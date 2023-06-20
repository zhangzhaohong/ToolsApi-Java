package com.koala.factory.extra.kugou;

import com.koala.service.utils.MD5Utils;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 11:54
 * @description
 */
public class KugouMidGenerator {
    public static String getMid() {
        return MD5Utils.md5(getRandomData() + getRandomData() + "-" + getRandomData() + "-" + getRandomData() + "-" + getRandomData() + "-" + getRandomData() + getRandomData() + getRandomData());
    }

    private static String getRandomData() {
        return RandomStringUtils.randomAlphanumeric(4);
    }
}
