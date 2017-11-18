package com.infoshareacademy.searchengine;

import com.infoshareacademy.searchengine.dao.UsersRepositoryDao;
import com.infoshareacademy.searchengine.dao.UsersRepositoryDaoBean;
import com.infoshareacademy.searchengine.domain.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!\n");
        UsersRepositoryDao usersRepositoryDao = new UsersRepositoryDaoBean();
        List<User> userList = usersRepositoryDao.getUsersList();
        for (User user : userList) {
            System.out.println(user.getName());
        }
    }
}
