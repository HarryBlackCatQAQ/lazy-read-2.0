package com.hhr.lazyread.dao;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.User;
import com.hhr.lazyread.model.UserExample;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDao继承基类
 */
@Repository
public interface UserDao extends MyBatisBaseDao<User, Integer, UserExample> {
    @Select("SELECT * FROM user where userName = #{userName} and password = #{password}")
    public User selectByUserUserNameAndPassword(User user);


    @Select("SELECT * FROM user where userName = #{userName}")
    public User selectByUserUserName(User user);



    @Select("select b.id,b.`name`,b.imageUrl,b.author,b.bookUrl,b.latestChapterName " +
            "from user_book ub " +
            "JOIN book b on b.id = ub.bookId " +
            "and ub.userId = #{id}")
//    @Results({
//            @Result(column="id", property="id"),
//            @Result(column="name", property="name"),
//            @Result(column="imageUrl", property="imageUrl"),
//            @Result(column="author", property="author"),
//            @Result(column="bookUrl", property="bookUrl"),
//            @Result(column="latestChapterName", property="latestChapterName")
//    })
    public List<Book> selectUserCollectionBooks(User user);
}