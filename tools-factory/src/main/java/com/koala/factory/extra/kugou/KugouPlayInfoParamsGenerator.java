package com.koala.factory.extra.kugou;

import com.koala.service.utils.MD5Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 13:07
 * @description
 */
public class KugouPlayInfoParamsGenerator {
    public static Map<String, String> getPlayInfoParams(String hash, String mid, String albumId, KugouCustomParamsUtil customParams) {
        String userId = customParams.getKugouCustomParams().get("userId").toString();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("cmd", "26");
        params.put("pid", "4");
        params.put("authType", "1");
        params.put("hash", hash);
        params.put("key", getKey(hash, mid, userId));
        params.put("behavior", "play");
        params.put("module", "");
        params.put("appid", "1155");
        params.put("mid", mid);
        params.put("userid", userId);
        params.put("token", customParams.getKugouCustomParams().get("token").toString());
        params.put("version", "3.1.2");
        params.put("vipType", "6");
        params.put("album_id", albumId);
        return params;
    }

    private static String getKey(String hash, String mid, String userId) {
        return MD5Utils.md5(hash + "kgcloudv21155" + mid + userId);
    }
}
