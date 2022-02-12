package com.koala.tools.utils;

import com.koala.tools.enums.ResponseEnums;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.koala.tools.enums.ResponseEnums.GET_FILE_SUCCESS;
import static com.koala.tools.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 18:30
 * @description
 */
@Getter
public class LanZouUtil {

    private final String password;
    private final String url;
    private final String urlId;
    private final String pageInfo;
    private String response;
    private static final Logger logger = LoggerFactory.getLogger(LanZouUtil.class);
    private static final ArrayList<String> hostList = new ArrayList<>();
    private static final HashMap<Integer, List<String>> invalidList = new HashMap<>();

    static {
        hostList.add("https://wwwx.lanzoux.com");
        hostList.add("https://www.lanzoui.com");
        hostList.add("https://www.lanzouw.com");
        hostList.add("https://wwx.lanzouj.com");
        invalidList.put(201, Arrays.asList("文件取消分享了", "文件不存在", "访问地址错误，请核查"));
        invalidList.put(202, List.of("输入密码"));
        invalidList.put(203, List.of("输入密码"));
        invalidList.put(204, List.of("显示更多文件"));
    }

    public LanZouUtil(String url, String password) throws IOException, URISyntaxException {
        this.url = url;
        this.password = password;
        this.urlId = getUrlId();
        logger.info("current urlId: {}", this.urlId);
        this.pageInfo = getInfo(0, this.urlId, 1);
        logger.info("current pageInfo: {}", this.pageInfo);
    }

    private String getUrlId() {
        if (StringUtils.isEmpty(this.url)) {
            return null;
        }
        String rule = "com/";
        return this.url.substring(this.url.lastIndexOf(rule) + rule.length(), Objects.equals(this.url.lastIndexOf("/"), this.url.lastIndexOf(rule) + rule.length() - 1) ? this.url.length() : this.url.lastIndexOf("/"));
    }

    private String getInfo(int index, String id, int mode) throws IOException, URISyntaxException {
        if (Objects.equals(index, (long) hostList.size() - 1)) {
            if (Objects.equals(mode, 2L)) {
                response = formatRespData(ResponseEnums.GET_DATA_ERROR, null);
                return null;
            } else {
                mode++;
                return getInfo(0, id, mode);
            }
        } else {
            String info = getUrl(index, id, mode);
            if (StringUtils.isEmpty(info)) {
                index++;
                return getInfo(index, id, mode);
            } else {
                // $GLOBALS['right'] = urls[$key];
                return info;
            }
        }
    }

    private String getUrl(int index, String id, int mode) throws IOException, URISyntaxException {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(hostList.get(index));
        if (Objects.equals(mode, 1L)) {
            joiner.add("/tp/");
        } else {
            joiner.add("/");
        }
        joiner.add(id);
        String tmp = joiner.toString();
        logger.info("current file: {}", tmp);
        return HttpClientUtil.doGet(tmp, getHeader(), new HashMap<>(0));
    }

    private HashMap<String, String> getHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.8");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
        return header;
    }

    public Map<Integer, String> checkStatus() {
        HashMap<Integer, String> result = new HashMap<>(0);
        invalidList.forEach((key, item) -> item.forEach(value -> {
            if (this.pageInfo.contains(value)) {
                result.put(key, value);
            }
        }));
        if (result.size() <= 0) {
            result.put(GET_FILE_SUCCESS.getCode(), GET_FILE_SUCCESS.getMessage());
        }
        return result;
    }
}
