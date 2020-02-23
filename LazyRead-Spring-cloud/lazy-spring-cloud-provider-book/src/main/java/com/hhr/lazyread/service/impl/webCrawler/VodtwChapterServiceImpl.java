package com.hhr.lazyread.service.impl.webCrawler;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.dao.ChapterDao;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.model.HtmlUnit;
import com.hhr.lazyread.service.ChapterAsyncDaoService;
import com.hhr.lazyread.service.ChapterWebCrawlerService;
import com.hhr.lazyread.service.VodtwWeb;
import com.hhr.lazyread.util.VodtwContentStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Harry
 * @Date: 2019-12-29 00:00
 * @Version 1.0
 */

@Service
@Slf4j
public class VodtwChapterServiceImpl implements ChapterWebCrawlerService, VodtwWeb {

    @Autowired
    private HtmlUnit htmlUnit;

    @Autowired
    private ChapterAsyncDaoService chapterAsyncDaoService;

    @Autowired
    private VodtwContentStringUtil vodtwContentStringUtil;

    @Autowired
    private ChapterDao chapterDao;

    public List<Chapter> getChapterList(Book book){
        List<Chapter> chapterList = new ArrayList<>();


        HtmlPage page = htmlUnit.getHtmlPage(book.getBookUrl());

        Document document = Jsoup.parse(page.asXml());
        page.cleanUp();

        Elements elements = document.select("body > div.wrapper_list > div.insert_list > dl > dd > ul > li > a");

        int counter = 1;
        for(Element element : elements){
//            System.out.println("第" + (counter) + "章");
            int idx = book.getBookUrl().lastIndexOf('/');
            Chapter chapter = new Chapter();
            chapter.setUrl(book.getBookUrl().substring(0,idx) + "/" + element.attr("href"));
            chapter.setTitle(element.text());
            chapter.setBookId(book.getId());

            chapterList.add(chapter);

            counter++;

        }

        log.info(book.getName() + "一共有" + counter + "章!");

        chapterAsyncDaoService.save(chapterList);

        return chapterList;
    }


    @Override
    public Chapter getChapterContent(Chapter chapter) {

        String content = chapterDao.selectChapterContentByChapterUrl(chapter.getUrl());
//        System.err.println(content);
        if(content == null){
            HtmlPage htmlPage = htmlUnit.getHtmlPage(chapter.getUrl());


            Document document = Jsoup.parse(htmlPage.asXml());
            htmlPage.cleanUp();


            Element element1 = document.select("#BookText").get(0);

            chapter.setContent(vodtwContentStringUtil.contentReplace(element1.text()));

            chapterDao.updateByPrimaryKey(chapter);
        }
        else{
            chapter.setContent(content);
        }
        return chapter;
    }
}