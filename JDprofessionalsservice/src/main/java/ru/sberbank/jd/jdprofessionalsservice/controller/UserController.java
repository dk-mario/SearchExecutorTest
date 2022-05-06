package ru.sberbank.jd.jdprofessionalsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.jd.jdprofessionalsservice.model.UsersEntity;
import ru.sberbank.jd.jdprofessionalsservice.service.CustomUserDetailedService;


/**
 * Контроллер для работы с пользователями:
 * 1. Создание пользователей
 * 2. Просмотр профиля пользователя
 * 3. Просмот списка пользователей (администратор)
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private CustomUserDetailedService customUserDetailedService;

/* // только для админов
    @GetMapping("/list")
    public String usersList(Model model) {
        return "users-list";
    }
*/

/*
    // профиль
    @GetMapping("/profile")
    public String usersList(Model model) {
        return "user-profile";
    }
*/


    @GetMapping("/create")
    public String userCreatePage(Model model) {
        UsersEntity newUser = new UsersEntity();
        model.addAttribute("newUser", newUser);
        return "user-create";
    }

    @PostMapping("/create")
    public String userCreate(UsersEntity user) {
        customUserDetailedService.saveUser(user);
        return "redirect:/login";
    }


}
