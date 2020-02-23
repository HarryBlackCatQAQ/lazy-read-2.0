package com.hhr.lazyread;

import com.hhr.lazyread.dao.BookDao;
import com.hhr.lazyread.dao.ChapterDao;
import com.hhr.lazyread.model.Book;
import com.hhr.lazyread.model.Chapter;
import com.hhr.lazyread.service.impl.webCrawler.VodtwBookServiceImpl;
import com.hhr.lazyread.service.impl.webCrawler.VodtwChapterServiceImpl;
import com.hhr.lazyread.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LazySpringCloudProviderBookApplicationTests {


    @Autowired
    BookDao bookDao;

    @Autowired
    VodtwBookServiceImpl vodtwBookService;

    @Autowired
    ChapterDao chapterDao;

    @Autowired
    VodtwChapterServiceImpl vodtwChapterService;

    @Autowired
    RedisUtil redisUtil;


    @Test
    void contextLoads() {
        Book book = new Book();

        System.err.println(redisUtil.getExpire("bookSearching::医生"));

//        System.err.println(redisUtil.get("token_root"));

//        System.err.println(bookDao.selectByPrimaryKey(3));

//        System.err.println(vodtwBookService.getBookList("斗罗大陆"));


//        System.err.println(bookDao.selectBookIdByBookUrl("https://www.vodtw.la/html/book/67/67494/index.html2"));

//        System.err.println(chapterDao.selectChapterIdByChapterUrl("https://www.vodtw.la/html/book/67/67494/43715359.html"));


//        Chapter chapter = new Chapter();
//        chapter.setTitle("@@@@@!!!hahahah");
//        chapterDao.insert(chapter);


//        Chapter chapter = new Chapter();
//        chapter.setUrl("https://www.vodtw.la/html/book/67/67494/49745376.html");
//        System.out.println(vodtwChapterService.getChapterContent(chapter));

//        System.out.println(chapterDao.selectChapterContentByChapterUrl("https://www.vodtw.la/html/book/67/67494/49745376.html"));
    }

}
