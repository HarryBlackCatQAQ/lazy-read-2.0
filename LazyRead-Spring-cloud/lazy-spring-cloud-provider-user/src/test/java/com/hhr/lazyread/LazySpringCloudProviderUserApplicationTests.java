package com.hhr.lazyread;

import com.hhr.lazyread.dao.UserBookDao;
import com.hhr.lazyread.dao.UserDao;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.model.UserBook;
import com.hhr.lazyread.service.UserService;
import com.hhr.lazyread.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LazySpringCloudProviderUserApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    UserBookDao userBookDao;

    @Autowired
    RedisUtil redisUtil;

//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
//        String aa = bCryptPasswordEncoder.encode("123456");
//        System.err.println(aa);
//        System.err.println(bCryptPasswordEncoder.matches("123456",aa));
//        User user = new User();
//
//        user.setUserName("root2");
//        user.setNickName("harry");
//        user.setPassword("123456");
//        user.setId(1);
//
////        userService.registeredUser(user);
////        System.out.println(userDao.selectUserCollectionBooks(user));\
//
////        System.err.println( userDao.insert(user));
//
//        UserBook userBook = new UserBook(1,185);
////        userBookDao.insert(userBook);
//
//        System.err.println(redisUtil.hasKey("token_root"));
//        redisUtil.del("token_root");
    }

}
