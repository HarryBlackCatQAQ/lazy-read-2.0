package com.hhr.lazyread;

import com.hhr.lazyread.service.scheduling.UpdateBookScheduling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Harry
 * @Date: 2020-04-09 23:26
 * @Version 1.0
 */

@SpringBootTest
public class UpdateBookSchedulingTest {

    @Autowired
    private UpdateBookScheduling updateBookScheduling;

    @Test
    public void test1(){
        updateBookScheduling.scheduledDBFile();
    }
}