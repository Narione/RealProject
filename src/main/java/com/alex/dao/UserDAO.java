package com.alex.dao;

import java.util.List;

import com.alex.entity.User;

public interface UserDAO {
    public int createUser(User user);
    public User updateUser(User user);
    public void deleteUser(int id);
    public List<User> getAllUsers();
    public User getUser(int id);
    public List<User> getAllUsers(String userName);
    public void createDemoData();
}
