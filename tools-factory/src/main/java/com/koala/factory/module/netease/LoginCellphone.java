package com.koala.factory.module.netease;

import cn.hutool.json.JSONObject;
import com.koala.base.module.BaseModuleWeApi;
import com.koala.service.utils.CryptoUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/7/3 22:51
 * @description
 */
@Service
public class LoginCellphone implements BaseModuleWeApi {


    @Override
    public void execute(JSONObject object, Map<String, String> queryMap, Map<String, String> cookies) {
        cookies.put("os", "pc");
        object.set("phone", queryMap.get("phone"));
        object.set("countrycode", Optional.ofNullable(queryMap.get("countrycode")).orElse("86"));
        String password = Optional.ofNullable(queryMap.get("md5_password")).orElse(CryptoUtil.getMd5(queryMap.get("password")));
        object.set("rememberLogin", true);
        object.set("password", password);
    }

    @Override
    public String getUrl() {
        return "https://music.163.com/weapi/login/cellphone";
    }
}
