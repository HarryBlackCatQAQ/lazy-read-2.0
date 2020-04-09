package com.hhr.lazyread.controller;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.service.BookWebCrawlerService;
import com.hhr.lazyread.service.scheduling.UpdateBookScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 15:39
 * @Version 1.0
 */

@RestController
public class BookController {

    @Autowired
    private BookWebCrawlerService bookWebCrawlerService;

    @Autowired
    private UpdateBookScheduling updateBookScheduling;

    @Cacheable(value = "bookSearching",key = "#content")
    @PostMapping("/search")
    public List<Book> search(@RequestBody String content){
        System.err.println(content);
        return bookWebCrawlerService.getBookList(content);
    }

    @GetMapping("/update")
    public String update(){
        updateBookScheduling.scheduledDBFile();
        return "ok";
    }
}