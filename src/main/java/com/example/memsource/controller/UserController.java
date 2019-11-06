package com.example.memsource.controller;

import com.example.memsource.dto.UserCredentialsDto;
import com.example.memsource.service.ProjectService;
import com.example.memsource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/user")
    public ModelAndView getAllUsers(Model model) {
        List<UserCredentialsDto> users = userService.getAllUsers();

        ModelAndView mav = new ModelAndView();
        model.addAttribute("users", users);

        mav.setViewName("index");
        mav.addObject("users", users);

        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") long userId) {
        UserCredentialsDto user = userService.getUserById(userId);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("update_user");
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/signUp")
    public ModelAndView showSignUpForm() {
        ModelAndView mav = new ModelAndView("add_user");
        mav.addObject("user", new UserCredentialsDto());
        return mav;
    }

    @PostMapping("/addUser")
    public ModelAndView addUser( @Valid UserCredentialsDto user, BindingResult result) {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.setViewName( "add_user");
        }

        userService.saveUser(user);
        mav.setViewName("index");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable("id") long userId, @Valid UserCredentialsDto user,
                             BindingResult result, Model model) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            user.setUserId(userId);
            mav.setViewName("update_user");
            return mav;
        }

        userService.updateUser(user, userId);
        mav.setViewName("index");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long userId) {
        UserCredentialsDto user = userService.getUserById(userId);
        userService.deleteUser(userId);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

}
