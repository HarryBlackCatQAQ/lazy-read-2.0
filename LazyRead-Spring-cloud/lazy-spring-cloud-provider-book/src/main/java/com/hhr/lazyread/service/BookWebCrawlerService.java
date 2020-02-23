package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;

import java.util.List;

public interface BookWebCrawlerService {

    public List<Book> getBookList(String content);
}
