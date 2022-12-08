package com.koala.tools.mail;

public class MailConfig {
    public static Integer corePoolSize = 4;
    public static Integer maxPoolSize = 4;
    public static Long expireTime = 7L * 24 * 60 * 60;
    public static String[] errorSuffix = new String[]{"co", "con"};
}
