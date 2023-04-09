package com.koala.tools.utils;

import com.google.gson.Gson;
import com.koala.tools.models.xbogus.XbogusDataModel;
import com.koala.tools.models.xbogus.XbogusRespDataModel;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/9 11:09
 * @description
 */
public class XbogusUtil {
    private static final String HOST = "https://signature-project.vercel.app/";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";

    public static XbogusDataModel encrypt(String url) throws IOException {
        Map<String, String> params = new HashMap<>(0);
        params.put("url", url);
        params.put("userAgent", USER_AGENT);
        String response = HttpClientUtil.doPostJson(HOST, GsonUtil.toString(params));
        if (!StringUtils.isEmpty(response)) {
            XbogusRespDataModel respData = GsonUtil.toBean(response, XbogusRespDataModel.class);
            if (!Objects.isNull(respData) && !Objects.isNull(respData.getData()) && !Objects.isNull(respData.getData().getUrl()) && !StringUtils.isEmpty(respData.getData().getUrl())) {
                return respData.getData();
            }
            return null;
        }
        return null;
    }
}
