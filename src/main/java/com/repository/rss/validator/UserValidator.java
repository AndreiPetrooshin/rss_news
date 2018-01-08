package com.repository.rss.validator;


import com.repository.rss.domain.User;
import com.repository.rss.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserServiceImpl userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if (user.getLogin().length() < 4 || user.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.userForm.username");
        }

        String login = user.getLogin();
        User byLogin = userService.findByLogin(login);
        if (byLogin != null) {
            errors.rejectValue("login", "Duplicate.userForm.username");
        }

        String email = user.getEmail();
        User byEmail = userService.findByEmail(email);
        if (byEmail != null) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().length() < 8) {
            errors.rejectValue("email", "Size.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }


    }
}
