package com.hhr.lazyread.dao;

import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.model.ChapterExample;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * ChapterDao继承基类
 */
@Repository
public interface ChapterDao extends MyBatisBaseDao<Chapter, Integer, ChapterExample> {

    @Select("select id from chapter where url = #{url}")
    public Integer selectChapterIdByChapterUrl(String url);

    @Select("select content from chapter where url = #{url}")
    public String selectChapterContentByChapterUrl(String url);
}