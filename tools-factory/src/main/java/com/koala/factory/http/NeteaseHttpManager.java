package com.koala.factory.http;

import com.google.gson.Gson;
import com.koala.data.models.RespModel;
import com.koala.service.data.redis.RedisKeyPrefix;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.XbogusUtil;
import org.apache.hc.client5.http.cookie.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.koala.service.utils.HeaderUtil.getNeteaseHttpHeader;

/**
 * @author koala
 * @version 1.0
 * @date 2023/7/3 16:24
 * @description
 */
@Component
public class NeteaseHttpManager {

    private static final String BASE_URL = "http://music.163.com";
    private static final String TYPE_WEAPI = "weapi";
    private static final String BASE_LINUX_API = "linuxapi";
    private static final String BASE_EAPI = "eapi";
    private static String servicePath;
    private final RedisService redisService;

    private String csrf = null;
    private String cookieStr = null;

    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @Value("${netease.generator}")  //删除掉static
    public void setServicePath(String servicePath) {
        NeteaseHttpManager.servicePath = servicePath;
    }

    public NeteaseHttpManager(RedisService redisService) {
        this.redisService = redisService;
    }

    public static Long getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return Long.parseLong(formatter.format(currentTime));
    }

    private void newInstance() throws IOException, URISyntaxException {
        String key = RedisKeyPrefix.NETEASE_CSRF_COOKIE + getCurrentDate();
        String publicKey = RedisKeyPrefix.NETEASE_PUBLIC_COOKIE + getCurrentDate();
        List<Cookie> cookies = HttpClientUtil.doGetCookie(BASE_URL, getNeteaseHttpHeader(null), null);
        Optional<Cookie> cookie = cookies.stream().filter(item -> item.getName().equals("__csrf")).findFirst();
        cookie.ifPresent(value -> csrf = value.getValue());
        redisService.set(key, csrf, 24 * 60 * 60L);
        StringBuilder cookieString = new StringBuilder();
        cookies.forEach(item -> cookieString.append(" ").append(item.getName()).append("=").append(item.getValue()).append(";"));
        cookieStr = cookieString.toString().trim();
        redisService.set(publicKey, cookieString.toString().trim(), 24 * 60 * 60L);
    }

    public String requestWeapi(String url, LinkedHashMap<String, String> params, String customCookies) throws IOException, URISyntaxException {
        String key = RedisKeyPrefix.NETEASE_CSRF_COOKIE + getCurrentDate();
        String publicKey = RedisKeyPrefix.NETEASE_PUBLIC_COOKIE + getCurrentDate();
        String csrfToken = redisService.get(key);
        String cookie = redisService.get(publicKey);
        if (!StringUtils.hasLength(csrfToken) && !StringUtils.hasLength(cookie)) {
            newInstance();
            csrfToken = this.csrf;
            cookie = this.cookieStr;
        }
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        data.put("encryptType", TYPE_WEAPI);
        params.put("csrf_token", csrfToken);
        data.put("params", GsonUtil.toString(params));
        String response = HttpClientUtil.doPostJson(servicePath, GsonUtil.toString(data));
        String body = null;
        if (StringUtils.hasLength(response)) {
            RespModel resp = GsonUtil.toBean(response, RespModel.class);
            body = GsonUtil.toString(resp.getData());
        }
        return HttpClientUtil.doPostJson(url + "?csrf_token=" + csrfToken, HeaderUtil.getNeteaseHttpHeader(cookie + customCookies), body);
    }
}
