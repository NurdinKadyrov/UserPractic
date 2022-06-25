package com.company;


import com.company.enums.Gender;
import com.company.model.User;
import com.company.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        //Использовал времменый метод addUser.
        userService.addUser(new User(1, "Nurdin", 20, Gender.MALE));
        userService.addUser(new User(2, "Nurtilek", 26, Gender.MALE));
        userService.addUser(new User(3, "Aizada", 21, Gender.MALE));

        while (true) {
            System.out.println("""
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Press 1 to addUser
                    Press 2 to findById
                    Press 3 to deleteById
                    Press 4 ro getAllById
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");
            switch (scanner.nextInt()) {
                case 1 -> userService.addUser();
                case 2 -> userService.findById();
                case 3 -> userService.deleteById();
                case 4 -> userService.getAllUsers();
            }
        }
    }
}
