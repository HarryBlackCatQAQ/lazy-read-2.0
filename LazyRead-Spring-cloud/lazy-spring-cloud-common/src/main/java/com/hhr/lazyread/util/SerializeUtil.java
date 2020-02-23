package com.hhr.lazyread.util;

import org.springframework.stereotype.Component;

import java.io.*;

/**
 * 序列化工具
 *
 * @Author: Harry
 * @Date: 2019-09-26 01:09
 * @Version 1.0
 */
@Component
public class SerializeUtil {
    /**
     *
     * 序列化
     */
    public byte[] serialize(Object obj) {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;

        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);
            byte[] byteArray = baos.toByteArray();
            return byteArray;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public Object unSerialize(byte[] bytes) {

        ByteArrayInputStream bais = null;

        try {
            // 反序列化为对象
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
