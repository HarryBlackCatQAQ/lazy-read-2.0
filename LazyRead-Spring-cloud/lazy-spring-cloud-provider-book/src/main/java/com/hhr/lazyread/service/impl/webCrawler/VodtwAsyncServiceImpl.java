package com.hhr.lazyread.service.impl.webCrawler;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.service.NovelAsyncWebCrawlerService;
import com.hhr.lazyread.service.VodtwWeb;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author: Harry
 * @Date: 2019-12-28 14:16
 * @Version 1.0
 */
@Service
public class VodtwAsyncServiceImpl implements NovelAsyncWebCrawlerService, VodtwWeb {

    @Override
    @Async("asyncServiceExecutor")
    public Future<Book> crawlerBook(CountDownLatch latch,HtmlPage page1,String url){
        Document document = Jsoup.parse(page1.asXml());

        Element element2 = document.select("body > div.wrapper_list > div.bookinfo.clearfix > div.bookpic > img").get(0);
        Element element1 = document.select("body > div.wrapper_list > div.bookinfo.clearfix > div.bookdetail > ul > li:nth-child(1) > h2").get(0);

//                System.out.println(element2.attr("src"));
//                System.out.println(element2.attr("alt"));
//                System.out.println(element1.text());

        Book book = new Book();

        getLatestChapterInfo(document,book);

        String author = element1.text();
        author = author.replaceAll("作 者：","");
        book.setAuthor(author);
        book.setImageUrl(element2.attr("src"));
        book.setName(element2.attr("alt"));
        book.setBookUrl(url);
        book.setWebType(webType);
        page1.cleanUp();

//                System.out.println(book);


//        System.out.println(book + "@@@");

        latch.countDown();
        return new AsyncResult<>(book);
    }

    private void getLatestChapterInfo(Document document,Book book){
        int len = document.select("body > div.wrapper_list > div.insert_list > dl > dd > ul > li > a").size();
        Element element3 = document.select("body > div.wrapper_list > div.insert_list > dl > dd > ul > li > a").get(len - 1);
//        System.out.println(len);

        String name = element3.text();
//        System.out.println(name);

        book.setLatestChapter(len);
        book.setLatestChapterName(name);

//        System.out.println(book.getLatestChapter());
//        System.out.println(book.getLatestChapterName());
    }
}