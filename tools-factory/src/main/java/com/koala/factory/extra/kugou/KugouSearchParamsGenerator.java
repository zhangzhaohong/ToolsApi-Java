package com.koala.factory.extra.kugou;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/19 20:38
 * @description
 */
public class KugouSearchParamsGenerator {

    private static final String CURRENT_UUID = UUID.randomUUID().toString().replace("-", "");

    public static String getSearchTextParams(Long timestamp, String key, Long page, Long limit, KugouCustomParamsUtil customParams) {
        String[] paramsArray = {
                "NVPh5oo715z5DIWAeQlhMDsWXXQV4hwt",
                "bitrate=0",
                // "callback=callback123",
                "clienttime=" + timestamp,
                "clientver=2000",
                "dfid=-",
                "filter=10",
                "inputtype=0",
                "iscorrection=1",
                "isfuzzy=0",
                "keyword=" + key,
                "mid=" + timestamp,
                "page=" + page,
                "pagesize=" + limit,
                "platform=WebFilter",
                "privilege_filter=0",
                "srcappid=2919",
                "token=" + customParams.getKugouCustomParams().get("token"),
                "userid=" + customParams.getKugouCustomParams().get("userId"),
                "uuid=" + CURRENT_UUID,
                "NVPh5oo715z5DIWAeQlhMDsWXXQV4hwt"
        };
        return String.join("", paramsArray);
    }

    public static Map<String, String> getSearchParams(Long timestamp, String key, Long page, Long limit, String signature, KugouCustomParamsUtil customParams) {
        Map<String, String> params = new HashMap<>();
        params.put("bitrate", "0");
        params.put("clienttime", String.valueOf(timestamp));
        params.put("clientver", "2000");
        params.put("dfid", "-");
        params.put("filter", "10");
        params.put("inputtype", "0");
        params.put("iscorrection", "1");
        params.put("isfuzzy", "0");
        params.put("keyword", key);
        params.put("mid", String.valueOf(timestamp));
        params.put("page", String.valueOf(page));
        params.put("pagesize", String.valueOf(limit));
        params.put("platform", "WebFilter");
        params.put("privilege_filter", "0");
        params.put("srcappid", "2919");
        params.put("token", customParams.getKugouCustomParams().get("token").toString());
        params.put("userid", customParams.getKugouCustomParams().get("userId").toString());
        params.put("uuid", CURRENT_UUID);
        params.put("signature", signature);
        return params;
    }
}
