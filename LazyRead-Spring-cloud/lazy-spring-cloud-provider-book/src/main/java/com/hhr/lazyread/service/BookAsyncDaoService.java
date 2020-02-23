package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Book;

import java.util.List;

public interface BookAsyncDaoService {

    public void save(List<Book> bookList);

    public void save(Book book);
}
