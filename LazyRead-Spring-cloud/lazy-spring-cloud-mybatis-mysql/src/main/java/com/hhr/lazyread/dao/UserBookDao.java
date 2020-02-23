package com.hhr.lazyread.dao;

import com.hhr.lazyread.model.UserBook;
import com.hhr.lazyread.model.UserBookExample;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

/**
 * UserBookDao继承基类
 */
@Repository
public interface UserBookDao extends MyBatisBaseDao<UserBook, Integer, UserBookExample> {

    @Delete("delete from user_book where userId = #{userId} and bookId = #{bookId}")
    public Integer deleteByuserIdAndBookId(Integer userId,Integer bookId);
}