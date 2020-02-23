package com.hhr.lazyread.service.impl.webCrawler;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.model.HtmlUnit;
import com.hhr.lazyread.service.*;
import com.hhr.lazyread.util.AsyncUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @Author: Harry
 * @Date: 2019-12-28 12:27
 * @Version 1.0
 */

@Service
@Slf4j
public class VodtwBookServiceImpl implements BookWebCrawlerService, VodtwWeb {


    @Autowired
    private HtmlUnit htmlUnit;

    @Autowired
    @Qualifier("vodtwAsyncServiceImpl")
    private NovelAsyncWebCrawlerService vodtwAsyncService;

    @Autowired
    private AsyncUtil asyncUtil;

    @Autowired
    private BookAsyncDaoService bookAsyncDaoService;


    public List<Book> getBookList(String content){
        List<Book> BookList = null;
        HtmlPage htmlPage = htmlUnit.getHtmlPage(vodtwUrl);

        HtmlInput input = null;

        try{
            // 获取搜索输入框
            input = (HtmlInput) htmlPage.getByXPath("//*[@id=\"searchwarpper\"]/form/ul/li[2]/input").get(0);
        }
        catch (IndexOutOfBoundsException e){
            log.warn("vodtw网页打不开，请稍后再试!");

            return BookList = new ArrayList<>();
        }


        input.setValueAttribute(content);

        // 获取搜索按钮
        HtmlInput btn = (HtmlInput) htmlPage.getByXPath("//*[@id=\"Image12\"]").get(0);
        htmlPage.cleanUp();

        // “点击” 搜索
        HtmlPage page2 = null;
        try {
            page2 = btn.click();
            BookList = AnalysisOfTheBook(page2);
            bookAsyncDaoService.save(BookList);
            page2.cleanUp();
        } catch (IOException e) {
            log.error("vodtw网页打不开，搜索失败，请稍后再试!");
            log.error(e.toString());
        }

        return BookList;
    }


    private List<Book> AnalysisOfTheBook(HtmlPage page){
        List<Book> BookList = new Vector<>();

        String urls[] = getBookUrl(page.asXml());

        CountDownLatch latch = new CountDownLatch(urls.length);

        List<Future<Book>> list = new ArrayList<>();
        for(int i = 0;i < urls.length;i++){

            HtmlPage page1 = htmlUnit.getHtmlPage(urls[i]);

            list.add(vodtwAsyncService.crawlerBook(latch,page1,urls[i]));

        }

        asyncUtil.toTargetList(latch,list,BookList);

//        htmlUnit.closeWebClient();
        return BookList;
    }

    private String[] getBookUrl(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("#CListTitle > a:nth-child(1)");

        String urls[] = new String[elements.size()];

        for(int i = 0;i < elements.size();i++){
            urls[i] = vodtwUrl + elements.get(i).attr("href");
        }

        return urls;
    }



}