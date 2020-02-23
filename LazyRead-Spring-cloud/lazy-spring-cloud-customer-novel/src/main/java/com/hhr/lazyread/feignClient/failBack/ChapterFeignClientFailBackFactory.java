package com.hhr.lazyread.feignClient.failBack;

import com.hhr.lazyread.feignClient.ChapterFeignClient;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 22:59
 * @Version 1.0
 */

@Component
@Slf4j
public class ChapterFeignClientFailBackFactory implements FallbackFactory<ChapterFeignClient> {
    @Override
    public ChapterFeignClient create(Throwable throwable) {
        return new ChapterFeignClient() {
            @Override
            public List<Chapter> getChapterList(Book book) {

                return new ArrayList<>();
            }

            @Override
            public Chapter getChapterContent(Chapter chapter) {
                warm();
                return chapter;
            }
        };
    }

    private void warm(){
        log.warn("ChapterFeignClient 降级!");
    }
}