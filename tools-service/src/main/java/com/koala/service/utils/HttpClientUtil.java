package com.koala.service.utils;

import com.google.common.base.Splitter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/12 14:07
 * @description
 */
public class HttpClientUtil {

    // 编码格式,发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";

    // 设置连接超时时间,单位毫秒
    private static final int CONNECT_TIMEOUT = 3 * 60 * 1000;

    // 请求响应超时时间,单位毫秒
    private static final int SOCKET_TIMEOUT = 3 * 60 * 1000;

    public static final String COOKIE_SPECS_STANDARD = "standard";

    public static List<Cookie> doGetCookie(String url, Map<String, String> headers, Map<String, String> params) throws IOException, URISyntaxException {
        CookieStore cookieStore = new BasicCookieStore();
        // 创建httpClient对象
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建访问地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpGet.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpGet);
            // 执行请求获取响应体并释放资源
            getHttpClientResult(httpClient, httpGet);
            return cookieStore.getCookies();
        }
    }

    public static List<Cookie> doGetCookie(String url, Map<String, String> params) throws IOException, URISyntaxException {
        return doGetCookie(url, null, params);
    }

    public static List<Cookie> doGetCookie(String url) throws IOException, URISyntaxException {
        return doGetCookie(url, null, null);
    }

    /**
     * 发送get请求;带请求头和请求参数
     */
    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) throws IOException, URISyntaxException {
        // 创建httpClient对象
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建访问地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpGet.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpGet);
            // 执行请求获取响应体并释放资源
            return getHttpClientResult(httpClient, httpGet);
        }
    }

    /**
     * 发送get请求;带请求参数
     */
    public static String doGet(String url, Map<String, String> params) throws IOException, URISyntaxException {
        return doGet(url, null, params);
    }

    /**
     * 发送get请求;不带请求头和请求参数
     */
    public static String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null, null);
    }

    /**
     * 发送post请求;带请求头和请求参数
     */
    public static String doPost(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        // 创建httpClient对象
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建http对象
            HttpPost httpPost = new HttpPost(url);
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpPost.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpPost);
            // 封装请求参数
            packageParam(params, httpPost);
            // 执行请求获取响应体并释放资源
            return getHttpClientResult(httpClient, httpPost);
        }
    }

    /**
     * 发送post请求;带请求参数
     */
    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, null, params);
    }

    /**
     * 发送post请求;不带请求头和请求参数
     */
    public static String doPost(String url) throws IOException {
        return doPost(url, null, null);
    }

    /**
     * 发送post请求;json格式参数
     */
    public static String doPostJson(String url, Map<String, String> headers, String json) throws IOException {
        // 创建httpClient对象
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建http对象
            HttpPost httpPost = new HttpPost(url);
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpPost.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpPost);
            // 封装请求参数为json格式
            packageJson(json, httpPost);
            // 执行请求获取响应体并释放资源
            return getHttpClientResult(httpClient, httpPost);
        }
    }

    /**
     * 发送post请求;json格式参数
     */
    public static String doPostJson(String url, String json) throws IOException {
        return doPostJson(url, null, json);
    }

    /*
     * 自定义的post
     * 返回Cookie
     * */
    public static List<Cookie> doPostJsonAndReturnCookie(String url, Map<String, String> headers, String json) throws IOException {
        // cookie
        CookieStore cookieStore = new BasicCookieStore();
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build()) {
            // 创建http对象
            HttpPost httpPost = new HttpPost(url);
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpPost.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpPost);
            // 封装请求参数为json格式
            packageJson(json, httpPost);
            // 执行请求获取响应体并释放资源
            getHttpClientResult(httpClient, httpPost);
            return cookieStore.getCookies();
        }
    }

    public static List<Cookie> doPostJsonAndReturnCookie(String url, String json) throws IOException {
        return doPostJsonAndReturnCookie(url, null, json);
    }

    /**
     * 发送put请求;带请求头和请求参数
     */
    public static String doPut(String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpPut.setConfig(requestConfig);
            packageHeader(headers, httpPut);
            packageParam(params, httpPut);
            return getHttpClientResult(httpClient, httpPut);
        }
    }

    /**
     * 发送put请求;带请求参数
     */
    public static String doPut(String url, Map<String, String> params) throws IOException {
        return doPut(url, null, params);
    }

    /**
     * 发送put请求;不带请求头和请求参数
     */
    public static String doPut(String url) throws IOException {
        return doPut(url, null, null);
    }

    /**
     * 发送delete请求;带请求头
     */
    public static String doDelete(String url, Map<String, String> headers, Map<String, String> params) throws IOException, URISyntaxException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            HttpDelete httpDelete = new HttpDelete(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpDelete.setConfig(requestConfig);
            packageHeader(headers, httpDelete);
            return getHttpClientResult(httpClient, httpDelete);
        }
    }

    /**
     * 发送delete请求;带请求参数
     */
    public static String doDelete(String url, Map<String, String> params) throws IOException, URISyntaxException {
        return doDelete(url, null, params);
    }

    /**
     * 发送delete请求;不带请求头和请求参数
     */
    public static String doDelete(String url) throws IOException, URISyntaxException {
        return doDelete(url, null, null);
    }

    public static String doGetRedirectLocation(String url, Map<String, String> headers, Map<String, String> params) throws IOException, URISyntaxException {
        // 创建httpClient对象
        int responseCode = 0;
        String location = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建访问地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).setRedirectsEnabled(false).build();
            httpGet.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpGet);
            // 执行请求获取响应体并释放资源
            // 执行请求
            CloseableHttpResponse httpResponse = null;
            try {
                // 获取响应体
                httpResponse = httpClient.execute(httpGet);
                responseCode = httpResponse.getCode();
                if (Objects.equals(responseCode, 302)) {
                    Header locationHeader = httpResponse.getFirstHeader("Location");
                    location = locationHeader.getValue();
                }
            } finally {
                // 释放资源
                if (!ObjectUtils.isEmpty(httpResponse)) {
                    httpResponse.close();
                }
            }
        }
        return location;
    }

    public static String doGetRedirectLocation(String url, Map<String, String> params) throws IOException, URISyntaxException {
        return doGetRedirectLocation(url, null, params);
    }

    public static String doGetRedirectLocation(String url) throws IOException, URISyntaxException {
        return doGetRedirectLocation(url, null, null);
    }

    public static int doGetResponseCode(String url, Map<String, String> headers, Map<String, String> params) throws IOException, URISyntaxException {
        // 创建httpClient对象
        int responseCode = 0;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建访问地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).setRedirectsEnabled(false).build();
            httpGet.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpGet);
            // 执行请求获取响应体并释放资源
            // 执行请求
            CloseableHttpResponse httpResponse = null;
            try {
                // 获取响应体
                httpResponse = httpClient.execute(httpGet);
                responseCode = httpResponse.getCode();
                return responseCode;
            } finally {
                // 释放资源
                if (!ObjectUtils.isEmpty(httpResponse)) {
                    httpResponse.close();
                }
            }
        }
    }

    public static int doGetResponseCode(String url, Map<String, String> params) throws IOException, URISyntaxException {
        return doGetResponseCode(url, null, params);
    }

    public static int doGetResponseCode(String url) throws IOException, URISyntaxException {
        return doGetResponseCode(url, null, null);
    }

    public static void doRelay(String url, Map<String, String> headers, Map<String, String> params, Integer successCode, Map<String, String> responseHeader, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        // 创建httpClient对象
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建访问地址
            URIBuilder uriBuilder = new URIBuilder(url);
            if (!ObjectUtils.isEmpty(params)) {
                params.forEach(uriBuilder::setParameter);
            }
            // 创建http对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置请求超时时间及响应超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS).setCookieSpec(COOKIE_SPECS_STANDARD).build();
            httpGet.setConfig(requestConfig);
            // 设置请求头
            packageHeader(headers, httpGet);
            // 执行请求获取响应体并释放资源
            // 执行请求
            CloseableHttpResponse httpResponse = null;
            try {
                // 获取响应体
                httpResponse = httpClient.execute(httpGet);
                HttpEntity entity = httpResponse.getEntity();
                if (Objects.equals(httpResponse.getCode(), successCode) && !Objects.isNull(entity)) {
                    responseHeader.forEach(response::addHeader);
                    response.addHeader("Content-Length", String.valueOf(entity.getContentLength()));
                    String rangeString = request.getHeader("Range");
                    if (!ObjectUtils.isEmpty(rangeString)) {
                        long range = Long.parseLong(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                        response.addHeader("Content-Range", String.valueOf(range + (entity.getContentLength()) - 1));
                    }
                    try (InputStream inputStream = entity.getContent(); ServletOutputStream targetStream = response.getOutputStream();) {
                        inputStream.transferTo(targetStream);
                        response.setContentLengthLong(entity.getContentLength());
                        targetStream.flush();
                    }
                }
            } finally {
                // 释放资源
                if (!ObjectUtils.isEmpty(httpResponse)) {
                    httpResponse.close();
                }
            }
        }
    }

    public static void doRelay(String url, Map<String, String> params, Integer successCode, Map<String, String> responseHeader, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        doRelay(url, null, params, successCode, responseHeader, request, response);
    }

    public static void doRelay(String url, Integer successCode, Map<String, String> responseHeader, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        doRelay(url, null, null, successCode, responseHeader, request, response);
    }

    /**
     * 设置请求头
     */
    private static void packageHeader(Map<String, String> headers, HttpUriRequestBase httpMethod) {
        if (!ObjectUtils.isEmpty(headers)) {
            headers.forEach(httpMethod::setHeader);
        }
    }

    /**
     * 封装请求参数
     */
    private static void packageParam(Map<String, String> params, HttpUriRequestBase httpMethod) {
        if (!ObjectUtils.isEmpty(params)) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            params.forEach((key, value) -> nameValuePairs.add(new BasicNameValuePair(key, value)));
            httpMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.defaultCharset()));
        }
    }

    /**
     * 封装请求参数为json格式
     */
    private static void packageJson(String json, HttpUriRequestBase httpMethod) {
        if (!ObjectUtils.isEmpty(json)) {
            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpMethod.setEntity(stringEntity);
        }
    }

    /**
     * 执行请求获取响应体并释放资源
     */
    private static String getHttpClientResult(CloseableHttpClient httpClient, HttpUriRequestBase httpMethod) throws IOException {
        // 执行请求
        CloseableHttpResponse httpResponse = null;
        try {
            // 获取响应体
            httpResponse = httpClient.execute(httpMethod);
            String content = "";
            if (!ObjectUtils.isEmpty(httpResponse) && !ObjectUtils.isEmpty(httpResponse.getEntity())) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return content;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            if (!ObjectUtils.isEmpty(httpResponse)) {
                httpResponse.close();
            }
            if (!ObjectUtils.isEmpty(httpClient)) {
                httpClient.close();
            }
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String getParam(String url, String name) {
        String params = url.substring(url.indexOf("?") + 1);
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        return split.get(name);
    }
}
