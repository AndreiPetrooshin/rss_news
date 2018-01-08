package com.repository.rss.repository;

import com.repository.rss.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByLogin(String login);

    User findByEmail(String email);
}
