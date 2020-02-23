package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public boolean hasUser(User user);

    public boolean registeredUser(User user);

    public Map<String,Object> loginUser(User user);

    public boolean logoutUser(User user);

    public List<Book> getUserCollectionBooks(User user);

    /**
     * map中装有 user 和 book 需要序列化
     * @param map
     * @return
     */
    public boolean collectionBook(Map<String,Object> map);

    /**
     * map中装有 user 和 book 需要序列化
     * @param map
     * @return
     */
    public Result deleteCollectionBook(Map<String,Object> map);

    public Result checkUserName(String userName);
}
