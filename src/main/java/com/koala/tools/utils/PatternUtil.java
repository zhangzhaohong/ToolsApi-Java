package com.koala.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/12 17:35
 * @description
 */
public class PatternUtil {

    public static String matchData(String regex, String data) {
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
