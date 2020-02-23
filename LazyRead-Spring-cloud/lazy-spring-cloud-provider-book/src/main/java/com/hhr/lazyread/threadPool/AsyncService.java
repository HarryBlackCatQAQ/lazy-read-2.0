package com.hhr.lazyread.threadPool;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.model.Book;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public interface AsyncService {

    /**
     *  执行异步任务
     *  可以根据需求，自己加参数拟定
     */
    void executeAsync();
}
