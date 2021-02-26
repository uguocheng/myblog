package com.gcnbl.service;

public interface UserService {
    int validateName(String username);

    void addBlogUser(String username, String password, Long telphone);

    Long login(String username, String password);

}
