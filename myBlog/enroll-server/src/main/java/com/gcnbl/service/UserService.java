package com.gcnbl.service;

public interface UserService {
    int validateName(String username);

    void addBlogUser(String username, String password, int telphone);

    Long login(String username, String password);

}
