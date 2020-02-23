package com.hhr.lazyread.service.impl;

import com.hhr.lazyread.dao.BookDao;
import com.hhr.lazyread.dao.UserBookDao;
import com.hhr.lazyread.dao.UserDao;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Result;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.model.UserBook;
import com.hhr.lazyread.redis.TokenRedis;
import com.hhr.lazyread.service.UserService;
import com.hhr.lazyread.util.JsonXMLUtils;
import com.hhr.lazyread.util.JwtTokenUtils;
import com.hhr.lazyread.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Harry
 * @Date: 2019-12-29 02:21
 * @Version 1.0
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService, TokenRedis {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserBookDao userBookDao;

    @Autowired
    private JsonXMLUtils jsonXMLUtils;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BookDao bookDao;

    @Override
    public boolean hasUser(User user) {
        User db = userDao.selectByUserUserName(user);

        return db.getUserName().equals(user.getUserName());
    }

    @Override
    public boolean registeredUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.insert(user) >= 1;
    }

    @Override
    public Map<String,Object> loginUser(User user) {
        User db = null;
        Map<String,Object> map = null;
        db = userDao.selectByUserUserName(user);

        if(db != null){
            if(bCryptPasswordEncoder.matches(user.getPassword(),db.getPassword())){
                map = new HashMap<>();
                map.put("user",db);
                String token = JwtTokenUtils.createToken(db,true);
                map.put("token", token);


                if(!redisUtil.set(TOKENKEY_PREFIX + user.getUserName(),token)){
                    log.error("redis 连接超时!请稍后再试!");
                }
            }
        }

//        db = userDao.selectByUserUserNameAndPassword(user);
//
//        if(db != null){
//            map = new HashMap<>();
//            map.put("user",db);
//            String token = JwtTokenUtils.createToken(db,true);
//            map.put("token", token);
//
//
//            if(!redisUtil.set(TOKENKEY_PREFIX + user.getUserName(),token)){
//                log.error("redis 连接超时!请稍后再试!");
//            }
//
//        }

        return map;
    }

    @Override
    public boolean logoutUser(User user) {
        String key = TOKENKEY_PREFIX + user.getUserName();
        if(redisUtil.hasKey(key)){
            redisUtil.del(key);
        }

        return true;
    }

    @Override
    public List<Book> getUserCollectionBooks(User user) {
        return userDao.selectUserCollectionBooks(user);
    }

    @Override
    public boolean collectionBook(Map<String,Object> map) {
        User user = null;
        Book book = null;
        try {
            user = jsonXMLUtils.map2obj(map.get("user"), User.class);
            book = jsonXMLUtils.map2obj(map.get("book"),Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        book.setId(bookDao.selectBookIdByBookUrl(book.getBookUrl()));

        UserBook userBook = new UserBook(user.getId(),book.getId());

        return userBookDao.insert(userBook) >= 1;
    }

    @Override
    public Result deleteCollectionBook(Map<String, Object> map) {
        Result result;
        User user = null;
        Book book = null;

        try {
            user = jsonXMLUtils.map2obj(map.get("user"), User.class);
            book = jsonXMLUtils.map2obj(map.get("book"),Book.class);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(false,Result.ERROR,Result.ERROR_MES);

            return result;
        }

        book.setId(bookDao.selectBookIdByBookUrl(book.getBookUrl()));

        UserBook userBook = new UserBook(user.getId(),book.getId());

        int tp = userBookDao.deleteByuserIdAndBookId(user.getId(),book.getId());
        System.err.println(tp);
        if(tp > 0){
            result = new Result(true,Result.OK,Result.OK_MES);
        }
        else{
            result = new Result(false,Result.ERROR,Result.ERROR_MES);
        }

        return result;
    }

    @Override
    public Result checkUserName(String userName) {
        Result result;

        User user = new User();
        user.setUserName(userName);

        User db = userDao.selectByUserUserName(user);
        System.err.println(db);
        if(db == null){
            result =  new Result(true,Result.OK,Result.OK_MES);
        }
        else{
            result =  new Result(false,Result.ERROR,Result.ERROR_MES);
        }

        return result;
    }
}