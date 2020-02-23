package com.hhr.lazyread.feignClient.failBack;

import com.hhr.lazyread.feignClient.UserFeignClient;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-12-29 13:56
 * @Version 1.0
 */

@Component
@Slf4j
public class UserFeignClientFailBackFactory implements FallbackFactory<UserFeignClient>{
    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public Map<String,Object> loginUser(User user) {
                warm();
                return null;
            }

            @Override
            public boolean logoutUser(User user) {
                warm();
                return false;
            }

            @Override
            public boolean registeredUser(User user) {
                warm();
                return false;
            }

            @Override
            public List<Book> getUserCollectionBooks(User user) {
                warm();
                return null;
            }

            @Override
            public boolean collectionBook(Map<String,Object> map) {
                warm();
                return false;
            }

            @Override
            public Result deleteCollectionBook(Map<String, Object> map) {
                warm();
                return new Result(false,Result.ERROR,"失败!服务降级!");
            }

            @Override
            public Result checkUserName(String userName) {
                warm();
                return new Result(false,Result.ERROR,"失败!服务降级!");
            }
        };
    }

    private void warm(){
        log.warn("UserFeignClient 降级!");
    }
}