package com.alex.dao.impl;

import com.alex.dao.UserDAO;
import com.alex.entity.User;
import com.alex.util.HibernateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Repository
public class UserDAOImpl implements UserDAO {

    public UserDAOImpl() {
        System.out.println("UserDAOImpl");
    }

    @Autowired
    private HibernateUtil hibernateUtil;

    @Override
    public int createUser(User user) {
        return (int) hibernateUtil.create(user);
    }

    @Override
    public User updateUser(User user) {
        return hibernateUtil.update(user);
    }

    @Override
    public void deleteUser(int id) {
        User user = new User();
        user.setId(id);
        hibernateUtil.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return hibernateUtil.fetchAll(User.class);
    }

    @Override
    public User getUser(int id) {
        return hibernateUtil.fetchById(id, User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers(String userName) {
        String query = "SELECT e.* FROM Users e WHERE e.name like '%"+ userName +"%'";
        List<Object[]> userObjects = hibernateUtil.fetchAll(query);
        List<User> users = new ArrayList<User>();
        for(Object[] userObject: userObjects) {
            User user = new User();
            int id = ((int) userObject[0]);
            int age = (int) userObject[1];
            String name = (String) userObject[2];
            boolean isAdmin = (boolean)userObject[3];
            Date date = (Date) userObject[4];
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setAdmin(isAdmin);
            user.setCreatedDate(date);
            users.add(user);
        }
        System.out.println(users);
        return users;
    }

    @Override
    public void createDemoData() {

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setAge(18 + (int)(Math.random() * ((60 - 18) + 1))); //generate random age from 18 - 60
            user.setAdmin(new Random().nextBoolean());
            hibernateUtil.create(user);
        }
    }

}