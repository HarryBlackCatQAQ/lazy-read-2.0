package com.hhr.lazyread.threadPool;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @Author: Harry
 * @Date: 2019-12-28 13:21
 * @Version 1.0
 */
@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        System.out.println("异步线程执行批量插入等耗时任务");
        log.info("end executeAsync");
    }

}