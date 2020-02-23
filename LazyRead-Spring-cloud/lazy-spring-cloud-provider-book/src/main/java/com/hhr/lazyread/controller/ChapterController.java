package com.hhr.lazyread.controller;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.service.ChapterWebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 22:21
 * @Version 1.0
 */

@RestController
public class ChapterController {

    @Autowired
    private ChapterWebCrawlerService chapterWebCrawlerService;

    @Cacheable(value = "getChapterList",key = "#book")
    @PostMapping("/getChapterList")
    public List<Chapter> getChapterList(@RequestBody Book book){
        return chapterWebCrawlerService.getChapterList(book);
    }

    @Cacheable(value = "getChapterContent",key = "#chapter")
    @PostMapping("/getChapterContent")
    public Chapter getChapterContent(@RequestBody Chapter chapter){
        return chapterWebCrawlerService.getChapterContent(chapter);
    }
}