package com.hhr.lazyread.service.impl;

import com.hhr.lazyread.feignClient.UserFeignClient;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2020-01-19 12:26
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Result loginUser(User user) {
        Result result;
        Map<String,Object> map = userFeignClient.loginUser(user);

        if(map == null){
            result = new Result(false,Result.LOGINERROR,Result.LOGINERROR_MES);
        }
        else{
            result = new Result(true,Result.OK,Result.OK_MES,map);
        }

        return result;
    }

    @Override
    public Result getUserCollectionBooks(User user) {
        Result result;

        List<Book> bookList = userFeignClient.getUserCollectionBooks(user);

        if(bookList == null){
           result = new Result(false,Result.ERROR,Result.ERROR_MES);
        }
        else{
            result = new Result(true,Result.OK,"获取书籍成功!",bookList);
        }

        return result;
    }

}