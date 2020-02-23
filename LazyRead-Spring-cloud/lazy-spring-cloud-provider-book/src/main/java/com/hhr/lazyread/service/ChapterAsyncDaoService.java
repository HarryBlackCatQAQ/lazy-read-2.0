package com.hhr.lazyread.service;

import com.hhr.lazyread.model.Chapter;

import java.util.List;

public interface ChapterAsyncDaoService {
    public void save(List<Chapter> chapterList);

    public void save(Chapter chapter);
}
