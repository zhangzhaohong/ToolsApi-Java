package com.koala.factory.module.netease;

import cn.hutool.json.JSONObject;
import com.koala.base.module.BaseModuleWeApi;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author koala
 * @version 1.0
 * @date 2023/7/3 22:51
 * @description
 */
@Service
public class LoginTokenRefreshGenerator implements BaseModuleWeApi {


    @Override
    public void execute(JSONObject object, Map<String, String> queryMap, Map<String, String> cookies) {
        cookies.put("os", "pc");
    }

    @Override
    public String getUrl() {
        return "https://music.163.com/weapi/login/token/refresh";
    }
}
