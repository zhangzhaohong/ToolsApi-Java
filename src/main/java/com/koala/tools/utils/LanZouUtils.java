package com.koala.tools.utils;

import com.koala.tools.enums.ResponseEnums;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

import static com.koala.tools.utils.RespUtils.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 18:30
 * @description
 */
public class LanZouUtils {

    private final String password;
    private final String url;
    private final String urlId;
    private ArrayList<String> urlList;
    private Object response;

    public LanZouUtils(String url, String password) {
        this.url = url;
        this.password = password;
        this.urlId = getUrlId();
    }

    private String getUrlId() {
        if (StringUtils.isEmpty(this.url)) {
            return null;
        }
        String rule = "com/";
        return this.url.substring(this.url.lastIndexOf(rule) + rule.length(), Objects.equals(this.url.lastIndexOf("/"), this.url.lastIndexOf(rule) + rule.length() - 1) ? this.url.length() : this.url.lastIndexOf("/"));
    }

    private Object getInfo(int index, String id, int mode) {
        if (Objects.equals(index, (long) urlList.size() - 1)) {
            if (Objects.equals(mode, 2L)) {
                response = formatRespData(ResponseEnums.GET_DATA_ERROR, null);
            } else {
                mode++;
                return getInfo(0, id, mode);
            }
        }
        return null;
    }

    private Object getUrl(int index, String id, int mode) {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(urlList.get(index));
        if (Objects.equals(mode, 1L)){
            joiner.add("/tp/");
        } else {
            joiner.add("/");
        }
        joiner.add(id);
        return null;
    }
}
