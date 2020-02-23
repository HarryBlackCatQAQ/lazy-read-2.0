package com.hhr.lazyread.feignClient;

import com.hhr.lazyread.feignClient.failBack.UserFeignClientFailBackFactory;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "lazy-spring-cloud-provider-user",fallbackFactory = UserFeignClientFailBackFactory.class)
public interface UserFeignClient {

    @PostMapping("/loginUser")
    public Map<String,Object> loginUser(User user);

    @PostMapping("/logoutUser")
    public boolean logoutUser(User user);

    @PostMapping("/registeredUser")
    public boolean registeredUser(User user);

    @PostMapping("/getUserCollectionBooks")
    public List<Book> getUserCollectionBooks(User user);

    @PostMapping("/collectionBook")
    public boolean collectionBook(Map<String,Object> map);

    @PostMapping("/deleteCollectionBook")
    public Result deleteCollectionBook(Map<String,Object> map);

    @PostMapping("/checkUserName")
    public Result checkUserName(String userName);
}
