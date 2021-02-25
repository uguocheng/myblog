package com.gcnbl.service;

public interface UserService {
    void addBlogUser(String username, String password, int telphone);

    Long login(String username, String password);

}
