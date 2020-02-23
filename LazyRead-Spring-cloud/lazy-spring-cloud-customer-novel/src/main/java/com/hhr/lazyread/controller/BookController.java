package com.hhr.lazyread.controller;

import com.hhr.lazyread.feignClient.BookSearchFeignClient;
import com.hhr.lazyread.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 15:48
 * @Version 1.0
 */

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookSearchFeignClient bookSearchFeignClient;

    @GetMapping("/search")
    public List<Book> search(String content){

        System.err.println(content);
        return bookSearchFeignClient.searchBook(content);
    }
}