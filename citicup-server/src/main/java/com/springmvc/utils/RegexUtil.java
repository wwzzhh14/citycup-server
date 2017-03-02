package com.springmvc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wzh on 16/8/13.
 */
public class RegexUtil {
    public static String matchRegex(String targetStr, String patternStr)
    {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容 // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配

        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        if (matcher.find())
        {
            // 打印出结果
            return matcher.group(1);
        }

        return "";
    }
}
