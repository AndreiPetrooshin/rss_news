package com.repository.rss.service;


public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
