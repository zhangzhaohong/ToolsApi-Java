package com.koala.tools.utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/12 18:07
 * @description
 */
public class IpUtil {

    private static Random random = new Random();

    private static ArrayList<String> availableIp = new ArrayList<>();

    static {
        availableIp.add("218");
        availableIp.add("66");
        availableIp.add("60");
        availableIp.add("202");
        availableIp.add("204");
        availableIp.add("59");
        availableIp.add("61");
        availableIp.add("222");
        availableIp.add("221");
        availableIp.add("62");
        availableIp.add("63");
        availableIp.add("64");
        availableIp.add("122");
        availableIp.add("211");
    }

    public static String getRandomIpAddress() {
        StringJoiner ipJoiner = new StringJoiner(".");
        ipJoiner.add(String.valueOf(availableIp.get(random.nextInt(availableIp.size() - 1))));
        ipJoiner.add(String.valueOf((random.nextInt(2550000 - 600000 + 1) + 600000) / 10000));
        ipJoiner.add(String.valueOf((random.nextInt(2550000 - 600000 + 1) + 600000) / 10000));
        ipJoiner.add(String.valueOf((random.nextInt(2550000 - 600000 + 1) + 600000) / 10000));
        return ipJoiner.toString();
    }

}
