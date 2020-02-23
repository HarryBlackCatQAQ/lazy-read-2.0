package com.hhr.lazyread.service;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.model.Book;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public interface NovelAsyncWebCrawlerService {
    Future<Book> crawlerBook(CountDownLatch latch, HtmlPage page,String url);
}
