package com.hhr.lazyread.controller;

import com.hhr.lazyread.feignClient.ChapterFeignClient;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 22:56
 * @Version 1.0
 */

@RestController
@RequestMapping("/book")
public class ChapterController {

    @Autowired
    private ChapterFeignClient chapterFeignClient;

    @PostMapping("/getChapterList")
    public List<Chapter> getChapterList(@RequestBody Book book){
        return chapterFeignClient.getChapterList(book);
    }

    @PostMapping("/getChapterContent")
    public Chapter getChapterContent(@RequestBody Chapter chapter){
        return chapterFeignClient.getChapterContent(chapter);
    }
}