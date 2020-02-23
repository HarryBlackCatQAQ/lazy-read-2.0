package com.hhr.lazyread.model;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Harry
 * @Date: 2019-12-28 12:17
 * @Version 1.0
 */
@Slf4j
@Component
public class HtmlUnit {
    private final static WebClient webClient = new WebClient(BrowserVersion.CHROME);

    public HtmlUnit(){

        // 取消 JS 支持
        webClient.getOptions().setJavaScriptEnabled(false);
        // 取消 CSS 支持
        webClient.getOptions().setCssEnabled(false);
        //设置超时
        webClient.getOptions().setTimeout(Integer.MAX_VALUE);
    }

    public HtmlPage getHtmlPage(String url){

        // 获取指定网页实体
        HtmlPage page = null;
        try {
            page = (HtmlPage) webClient.getPage(url);
            webClient.waitForBackgroundJavaScript(10000);
        } catch (Exception e) {
            log.error("爬虫网页打不开！");
            log.error(e.toString());
        }
        return page;
    }

    public void closeWebClient(){
        this.webClient.close();
    }
}