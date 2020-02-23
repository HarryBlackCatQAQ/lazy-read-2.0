package com.hhr.lazyread.util;

import com.hhr.lazyread.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: Harry
 * @Date: 2019-12-28 14:49
 * @Version 1.0
 */

@Component
@Slf4j
public class AsyncUtil {

    public <T,S> void toTargetList(CountDownLatch latch, List<T> forList,List<S> targetList){
        try {
            latch.await();

            for(T t:forList){
                S s = (S)(((Future<T>)t).get());
                targetList.add(s);
            }
        } catch (InterruptedException e) {
            log.error("异步加入targetList失败!");
            log.error(e.toString());
        } catch (ExecutionException e) {
            log.error("异步获取Future失败!");
            log.error(e.toString());
        }
    }
}