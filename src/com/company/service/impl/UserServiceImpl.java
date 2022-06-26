package com.company.service.impl;

import com.company.dao.UserDao;
import com.company.enums.Gender;
import com.company.exeptions.MyException;
import com.company.model.User;
import com.company.service.UserService;


import java.util.Scanner;


public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDao();
    Scanner scanner = new Scanner(System.in);

    //Временное метот для добавление USERов.
    public void addUser(User user) {
        userDao.getUserList().add(user);
    }

    @Override
    public void addUser() {
        try {
            System.out.println("Напишите своё имя: ");
            String name = scanner.nextLine();
            if (name.matches("^[0-9]*$") && name.length() > 2) {
                throw new MyException("Вы не правильно ввели имя !");
            }
            System.out.println("Напишите ID номер:");
            int id = scanner.nextInt();
            if (!searchID(id)) {
                throw new MyException("В базе имеется такое ID !");
            }
            System.out.println("Напишите своё возраст: ");
            int age = scanner.nextInt();
            if (age < 0 || age > 120) {
                throw new MyException("Вы не правильно ввели !");
            }
            System.out.println("Напишите своё пол: 1: MALE , 2: FEMALE");
            int s = scanner.nextInt();
            if (!(s == 1 || s == 2)) {
                throw new MyException("Вы не правильно выбирали !");
            }
            if (s == 1) {
                userDao.getUserList().add(new User(id, name, age, Gender.MALE));
            } else {
                userDao.getUserList().add(new User(id, name, age, Gender.FEMALE));
            }
            getAllUsers();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findById() {
        System.out.println("Напишите user ID: ");
        int id = scanner.nextInt();
        try {
            if (searchID(id)) {
                throw new MyException("В базе не имеется такое ID !");
            }
            userDao.getUserList().stream().filter(x -> x.getId() == id).forEach(System.out::println);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById() {
        System.out.println("Напишите user ID: ");
        int id = scanner.nextInt();
        try {
            if (searchID(id)) {
                throw new MyException("В базе не имеется такое ID !");
            }
            userDao.getUserList().removeIf(x -> x.getId() == id);
            userDao.getUserList().forEach(System.out::println);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getAllUsers() {
        try {
            if (userDao.getUserList().size() < 1) {
                throw new MyException("Наша база пусто !");
            }
            userDao.getUserList().forEach(System.out::println);
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private boolean searchID(int id) {
       return userDao.getUserList().stream().anyMatch(x -> x.getId() == id);
    }
}
