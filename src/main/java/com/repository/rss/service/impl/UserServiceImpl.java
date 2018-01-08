package com.repository.rss.service.impl;

import com.repository.rss.domain.Role;
import com.repository.rss.domain.User;
import com.repository.rss.repository.RoleDao;
import com.repository.rss.repository.UserDao;
import com.repository.rss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userDao.findOne(id);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    public void save(User user) {
        user.setPassword(encode.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findOne(2));
        user.setRoles(roles);
        userDao.save(user);
    }

    public boolean ifExist(Integer id) {
        return userDao.exists(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.delete(id);
    }
}
