package com.hhr.lazyread.util;

import org.springframework.stereotype.Component;

/**
 * @Author: Harry
 * @Date: 2019-05-27 14:42
 * @Version 1.0
 */

@Component
public class VodtwContentStringUtil {

    public String addNewLine(String content){
        String s[] = content.split("\n");
        String ans = "";
        for(int i = 0;i < s.length;i++){
            if(s[i].length() > 44){
                for(int j = 1;j * 44 <= s[i].length();j++){
                    StringBuilder stringBuilder = new StringBuilder(s[i]);
                    stringBuilder.insert(j * 44,'\n');

                    s[i] = stringBuilder.toString();
                }
            }

            s[i] += "\n";

            ans += s[i];
        }

        return ans;
    }

    public String contentReplace(String str){
//        System.out.println(str);
        str = deleteErrorContent(str);
        String tp = str;
        for(int i = 0;i < 2;i++){
            tp = replace(tp);
        }

//        String s[] = tp.split("\n");
//
//        for(int i = 0;i < s.length;i++){
//            System.out.println("idx " + i + " length  " + s[i].length() +  ": " + s[i]);
//        }

        tp = addNewLine(tp);
//        System.out.println(tp);
        return tp;
    }

    public String replace(String str){
        String tp = "";
        char pre = '0';
        for(int i = 0;i < str.length();i++) {
            char c = str.charAt(i);
            if (i != ' ' && pre == ' ') {
                tp += '\n';
            }
            tp += c;
            pre = c;
        }

        return tp;
    }

    public String deleteErrorContent(String content){
        for(int i = 0;i < errorConeten.length;i++){
            content = content.replaceAll(errorConeten[i],"");
        }

        return content;
    }

    private String errorConeten[] = {"手机阅读",
            "品=书/网",
            "https://wWw.Vodtw.la ",

            "�Q看 最 新�R�Q章 节�R�Q百 度�R �Q搜 索�R �Q 品 �R�Q 书 �R�Q �W �R",

            "�Q看 \n" +
            "最 \n" +
            "新�R�Q章 \n" +
            "节�R�Q百 \n" +
            "度�R \n" +
            "�Q搜 \n" +
            "索�R \n" +
            "�Q \n" +
            "品 \n" +
            "�R�Q \n" +
            "书 \n" +
            "�R�Q \n" +
            "�W \n" +
            "�R ",

            "≦看 最 新≧≦章 节≧≦百 度≧ ≦搜 索≧ ≦ 品 ≧≦ 书 ≧≦ 網 ≧",

            "�Q看",
            "新�R�Q章",
            "节�R�Q百",
            "度�R",
            "�Q搜",
            "索�R",
            "�Q",
            "�R�Q",
            "�R�Q",
            "�W",
            "�R",
            "�h",

            "�",

            "品书网手机端",

            "品 书 网 （w W W . V o Dtw . c o M）",

            "（w W W . V o Dtw . c o M）",

            "m.vodtW.com",

            "wWw.Vodtw.com",

            "www.vodtW.com",

            "<!go>",

            "<!over>"};
}