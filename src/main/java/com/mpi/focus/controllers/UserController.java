package com.mpi.focus.controllers;

import com.mpi.focus.models.Plan;
import com.mpi.focus.models.Role;
import com.mpi.focus.models.User;
import com.mpi.focus.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String userList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userlist";
    }

    @GetMapping("/user/{user}")
    public String userEditForm(@PathVariable(value = "user") Long id,
                               Model model) {
        User user = userRepository.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }


    @PostMapping("/user/{user}")
    public String planEditForm(@PathVariable(value = "user") Long id,
                               @RequestParam String username,
                               @RequestParam String login,
                               @RequestParam String password) {
        User user = userRepository.getById(id);
        user.setUsername(username);
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:";
    }

    @GetMapping("/newuser")
    public String plan(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "createUser";
    }

    @PostMapping("/newuser")
    public String addLegend(@RequestParam String username,
                            @RequestParam String login,
                            @RequestParam String password,
                            Model model) {
        User user = new User(username, login, password);
        userRepository.save(user);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "redirect:user";
    }
}
