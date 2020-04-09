package com.hhr.lazyread.dao;

import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.BookExample;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookDao继承基类
 */
@Repository
public interface BookDao extends MyBatisBaseDao<Book, Integer, BookExample> {


    @Select("select id from book where bookUrl = #{bookUrl}")
    public Integer selectBookIdByBookUrl(String bookUrl);

    @Select("select * from book")
    public List<Book> getAllBook();
}