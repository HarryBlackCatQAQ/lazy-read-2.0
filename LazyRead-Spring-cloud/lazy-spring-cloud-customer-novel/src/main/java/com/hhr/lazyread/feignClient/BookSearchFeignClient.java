package com.hhr.lazyread.feignClient;

import com.hhr.lazyread.config.FeignDisableHystrixConfiguration;
import com.hhr.lazyread.feignClient.failBack.BookSearchFeignClientFailBackFactory;
import com.hhr.lazyread.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "lazy-spring-cloud-provider-book",
        configuration = FeignDisableHystrixConfiguration.class,
        fallbackFactory = BookSearchFeignClientFailBackFactory.class)
public interface BookSearchFeignClient {


    @PostMapping("/search")
    public List<Book> searchBook(String content);
}