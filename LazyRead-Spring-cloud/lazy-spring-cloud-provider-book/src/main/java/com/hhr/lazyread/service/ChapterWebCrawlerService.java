package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;

import java.util.List;

public interface ChapterWebCrawlerService {

    public List<Chapter> getChapterList(Book book);

    public Chapter getChapterContent(Chapter chapter);
}
