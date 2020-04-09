package com.hhr.lazyread.service.scheduling;

import com.hhr.lazyread.dao.BookDao;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.service.ChapterWebCrawlerService;
import com.hhr.lazyread.service.impl.webCrawler.VodtwChapterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Harry
 * @Date: 2020-04-09 23:20
 * @Version 1.0
 */

@Component
@Slf4j
public class UpdateBookScheduling {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ChapterWebCrawlerService chapterWebCrawlerService;

    private static int num = 0;

    @Scheduled(cron = "0 35 2 * * ?")
    public void scheduledDBFile(){
        List<Book> dbBookList = bookDao.getAllBook();

//        System.err.println(dbBookList);
//        System.err.println(dbBookList.size());

//        ExecutorService pool = Executors.newCachedThreadPool();
//
//        int counter = 0;
//        for(Book book : dbBookList){
////            System.err.println(++counter);
//            pool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    List<Chapter> a = chapterWebCrawlerService.getChapterList(book);
////                    System.err.println(a + "@");
//                    num++;
//                }
//            });
//
//        }
//
//
//
//        pool.shutdown();
//        System.err.println(dbBookList.size() + " " + counter + " #!" + " num:" + num + "@!!!");
        test1(dbBookList);
    }


    public void test1(List<Book> dbBookList){
        int poolSize = 5;

        ExecutorService pool = Executors.newFixedThreadPool(poolSize * 2);
        for(int i = 0;i < dbBookList.size();i++){
            Book book = dbBookList.get(i);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    chapterWebCrawlerService.getChapterList(book);
                }
            });
            if(i % poolSize == 0){
                try {
                    Thread.sleep(1000 * 12);
                } catch (InterruptedException e) {
                    log.error("晚上爬虫计划任务失败!");
                    log.error(e.toString());
                }
            }
        }
    }
}