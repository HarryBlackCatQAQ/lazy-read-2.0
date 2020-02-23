package com.hhr.lazyread.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Harry
 * @Date: 2019-12-28 02:47
 * @Version 1.0
 */
@Slf4j
public class DynamicDataSourceHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    public static String getDbType() {
        String db = contextHolder.get();
        if (db == null) {
            db = DB_MASTER;
        }
        return db;
    }

    public static void setDBType(String str) {
        log.info("数据源为" + str);
        contextHolder.set(str);
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}