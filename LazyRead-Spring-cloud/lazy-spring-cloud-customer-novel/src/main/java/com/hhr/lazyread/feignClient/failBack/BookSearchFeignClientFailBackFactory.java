package com.hhr.lazyread.feignClient.failBack;

import com.hhr.lazyread.feignClient.BookSearchFeignClient;
import com.hhr.lazyread.model.Book;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 15:56
 * @Version 1.0
 */
@Component
@Slf4j
public class BookSearchFeignClientFailBackFactory implements FallbackFactory<BookSearchFeignClient> {
    @Override
    public BookSearchFeignClient create(Throwable throwable) {
        return new BookSearchFeignClient() {


            @Override
            public List<Book> searchBook(String content) {
                warm();
                return new ArrayList<>();
            }
        };
    }


    private void warm(){
        log.warn("BookFeignClient 降级!");
    }
}