package com.hhr.lazyread.service.impl;

import com.hhr.lazyread.dao.ChapterDao;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.service.ChapterAsyncDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-28 22:46
 * @Version 1.0
 */

@Service
@Transactional
@Async("asyncServiceExecutor")
public class ChapterAsyncDaoServiceImpl implements ChapterAsyncDaoService {

    @Autowired
    private ChapterDao chapterDao;

    @Override
    public void save(List<Chapter> chapterList) {
        for(Chapter chapter:chapterList){
            save(chapter);
        }
    }

    @Override
    public void save(Chapter chapter) {
        Integer id = hasChapter(chapter);
        if(id == null){
            chapterDao.insert(chapter);
        }
    }

    private Integer hasChapter(Chapter chapter){
        return chapterDao.selectChapterIdByChapterUrl(chapter.getUrl());
    }


}