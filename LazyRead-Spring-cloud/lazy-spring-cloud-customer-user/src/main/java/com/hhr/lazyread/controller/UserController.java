package com.hhr.lazyread.controller;

import com.hhr.lazyread.feignClient.UserFeignClient;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-12-29 13:30
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PostMapping("/logout")
    public boolean logoutUser(@RequestBody User user){
        return userFeignClient.logoutUser(user);
    }

    @PostMapping("/registeredUser")
    public boolean registeredUser(@RequestBody User user){
        return userFeignClient.registeredUser(user);
    }

    @PostMapping("/getUserCollectionBooks")
    public Result getUserCollectionBooks(@RequestBody  User user) {
        return userService.getUserCollectionBooks(user);
    }

    @PostMapping("/collectionBook")
    public boolean collectionBook(@RequestBody Map<String,Object> map){
        return userFeignClient.collectionBook(map);
    }

    @PostMapping("/deleteCollectionBook")
    public Result deleteCollectionBook(@RequestBody Map<String,Object> map){
        return userFeignClient.deleteCollectionBook(map);
    }

    @GetMapping("/checkUserName")
    public Result checkUserName(String userName){
        return userFeignClient.checkUserName(userName);
    }
}