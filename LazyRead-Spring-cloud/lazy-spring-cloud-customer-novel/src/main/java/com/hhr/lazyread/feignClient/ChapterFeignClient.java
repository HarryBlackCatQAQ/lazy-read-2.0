package com.hhr.lazyread.feignClient;

import com.hhr.lazyread.feignClient.failBack.ChapterFeignClientFailBackFactory;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "lazy-spring-cloud-provider-book",fallbackFactory = ChapterFeignClientFailBackFactory.class)
public interface ChapterFeignClient {

    @PostMapping("/getChapterList")
    public List<Chapter> getChapterList(Book book);

    @PostMapping("/getChapterContent")
    public Chapter getChapterContent(Chapter chapter);
}
