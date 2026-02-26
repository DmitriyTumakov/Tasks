package test.task;

import test.task.dao.impl.UserEntityDaoImpl;
import test.task.service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        final UserService userService = new UserService(new UserEntityDaoImpl());
        final Scanner scanner = new Scanner(System.in);

        int id = 0;
        String name = null;
        String email = null;
        int age = 0;

        while (true) {
            System.out.print("Введите запрос\n" +
                    "1. Создать пользователя: create_user name:[имя] email:[почта] age:[возраст]\n" +
                    "2. Обновить профиль: update_user name:[имя] email:[почта] age:[возраст]\n" +
                    "3. Удалить пользователя: delete_user [id]\n" +
                    "4. Получить данные о пользователе: get_user [id]\n");
            String[] command = scanner.nextLine().split(" ");
            switch (command[0]) {
                case "create_user":
                    if (command[1].contains("name")) {
                        name = command[1].split(":")[1];
                    } else {
                        System.out.println("Ошибка в команде!");
                    }
                    if (command[2].contains("email")) {
                        email = command[2].split(":")[1];
                    } else {
                        System.out.println("Ошибка в команде!");
                    }
                    if (command[3].contains("age")) {
                        age = Integer.parseInt(command[3].split(":")[1]);
                    } else {
                        System.out.println("Ошибка в команде!");
                    }
                    userService.createUser(name, email, age);
                    break;
                case "update_user":
                    for (String s : command) {
                        if (s.contains("id")) {
                            id = Integer.parseInt(s.split(":")[1]);
                        }
                        if (s.contains("name")) {
                            name = s.split(":")[1];
                        }
                        if (s.contains("email")) {
                            email = s.split(":")[1];
                        }
                        if (s.contains("age")) {
                            age = Integer.parseInt(s.split(":")[1]);
                        }
                    }
                    userService.updateUser(id, name, email, age);
                    break;
                case "delete_user":
                    id = Integer.parseInt(command[1]);
                    userService.deleteUser(id);
                    break;
                case "get_user":
                    id = Integer.parseInt(command[1]);
                    System.out.println(userService.getUserById(id).toString());
                    break;
            }
        }
    }
}