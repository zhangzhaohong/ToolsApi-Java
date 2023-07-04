package com.koala.factory.service.netease.impl;

import cn.hutool.json.JSONObject;
import com.koala.base.module.*;
import com.koala.factory.service.netease.BaseService;
import com.koala.service.utils.CookieUtil;
import com.koala.service.utils.CryptoUtil;
import com.koala.service.utils.RestTemplateUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class BaseServiceImpl implements BaseService {

    private final RestTemplate restTemplate;
    private final InitModule initModule;
    private final CookieUtil cookieUtil;

    @Override
    public ResponseEntity<String> doRequest(HttpServletRequest request) {
        Map<String, String> cookies = cookieUtil.getCookies(request);
        Map<String,String> queryMap = new ConcurrentHashMap<>();
        if (StringUtils.hasLength(request.getQueryString())) {
            String[] queryArray = request.getQueryString().split("&");
            for (String query : queryArray) {
                if (query.contains("=")) {
                    String[] split = query.split("=");
                    queryMap.put(split[0],split[1]);
                }
            }
        }
        String key = request.getRequestURI()
                .replaceAll("tools","")
                .replaceAll("Netease","")
                .replaceAll("/", "");

        JSONObject object = new JSONObject();
        object.set("csrf_token", cookies.get("__csrf"));
        BaseModule baseModule = initModule.getService(key);
        baseModule.execute(object,queryMap,cookies);
        if (baseModule instanceof BaseModuleApi) {
            return RestTemplateUtil.postApi(object, baseModule.getUrl(), cookies, restTemplate);
        }
        if (baseModule instanceof BaseModuleEApi) {
            final String param = CryptoUtil.eapiEncrypt(baseModule.getOptionsUrl(), object.toString());
            return RestTemplateUtil.postEApi(param,
                    baseModule.getUrl().replaceAll("api",baseModule.getOptionsUrl()),
                    cookies,
                    restTemplate);
        }
        if (baseModule instanceof BaseModuleWeApi) {
            String[] encrypt = CryptoUtil.weapiEncrypt(object.toString());
            return RestTemplateUtil.postWeApi(encrypt[0],
                    encrypt[1],
                    baseModule.getUrl().replaceAll("/api","/" + baseModule.getType()) +"?csrf_token=" + cookies.get("__csrf"),
                    cookies,
                    restTemplate);
        }
        if (baseModule instanceof BaseModuleGetType) {
            return RestTemplateUtil.get(baseModule.getUrl(),cookies,restTemplate);
        }
        return RestTemplateUtil.post(object, baseModule.getUrl(),cookies,restTemplate);
    }
}
