package com.hhr.lazyread.service.impl;

import com.hhr.lazyread.dao.BookDao;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.service.BookAsyncDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 20:46
 * @Version 1.0
 */

@Async("asyncServiceExecutor")
@Transactional
@Service
public class BookAsyncDaoServiceImpl implements BookAsyncDaoService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void save(List<Book> bookList) {
        for(Book book:bookList){
            save(book);
        }
    }


    @Override
    public void save(Book book) {
        Integer id = hasBook(book);
        if(id != null){
            book.setId(id);
            bookDao.updateByPrimaryKey(book);
        }
        else{
            bookDao.insert(book);
        }
    }

    private Integer hasBook(Book book){
        return bookDao.selectBookIdByBookUrl(book.getBookUrl());
    }
}