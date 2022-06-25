package com.company.dao;

import com.company.model.User;

import java.util.LinkedList;
import java.util.List;


public class UserDao {
    private final List<User> userList= new LinkedList<>();

    public List<User> getUserList() {
        return userList;
    }



}
