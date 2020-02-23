package com.hhr.lazyread.controller;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-12-29 13:33
 * @Version 1.0
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/loginUser")
    public Map<String,Object> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }


    @PostMapping("/logoutUser")
    public boolean logoutUser(@RequestBody User user){
        return userService.logoutUser(user);
    }

    @PostMapping("/registeredUser")
    public boolean registeredUser(@RequestBody User user){
        return userService.registeredUser(user);
    }

    @PostMapping("/getUserCollectionBooks")
    public List<Book> getUserCollectionBooks(@RequestBody  User user) {
        return userService.getUserCollectionBooks(user);
    }

    @PostMapping("/collectionBook")
    public boolean collectionBook(@RequestBody Map<String,Object> map){
        return userService.collectionBook(map);
    }

    @PostMapping("/deleteCollectionBook")
    public Result deleteCollectionBook(@RequestBody Map<String,Object> map){
        return userService.deleteCollectionBook(map);
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestBody String userName){
        System.err.println(userName);
        return userService.checkUserName(userName);
    }
}