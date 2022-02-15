package com.koala.tools.utils;

import com.koala.tools.enums.ResponseEnums;
import com.koala.tools.models.file.FileInfoModel;
import com.koala.tools.models.lanzou.VerifyPasswordResp;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.koala.tools.enums.ResponseEnums.GET_FILE_SUCCESS;
import static com.koala.tools.utils.HeaderUtil.*;
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
    private String host;
    private String response;
    private static final Logger logger = LoggerFactory.getLogger(LanZouUtil.class);
    private static final ArrayList<String> HOST_LIST = new ArrayList<>();
    private static final HashMap<Integer, List<String>> INVALID_LIST = new HashMap<>();

    static {
        HOST_LIST.add("https://wwwx.lanzoux.com");
        HOST_LIST.add("https://www.lanzoui.com");
        HOST_LIST.add("https://www.lanzouw.com");
        HOST_LIST.add("https://wwx.lanzouj.com");
        HOST_LIST.add("https://wwi.lanzouj.com");
        INVALID_LIST.put(201, Arrays.asList("文件取消分享了", "文件不存在", "访问地址错误，请核查"));
        INVALID_LIST.put(202, List.of("输入密码"));
        INVALID_LIST.put(203, List.of("显示更多文件"));
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
        if (Objects.equals(index, HOST_LIST.size() - 1)) {
            if (Objects.equals(mode, 2)) {
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
                host = HOST_LIST.get(index);
                return info;
            }
        }
    }

    private String getUrl(int index, String id, int mode) throws IOException, URISyntaxException {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(HOST_LIST.get(index));
        logger.info("mode: {}", mode);
        if (Objects.equals(mode, 1)) {
            joiner.add("/tp/");
        } else {
            joiner.add("/");
        }
        joiner.add(id);
        String tmp = joiner.toString();
        logger.info("current file: {}", tmp);
        return HttpClientUtil.doGet(tmp, getHeader(), new HashMap<>(0));
    }

    public Map<Integer, String> checkStatus() {
        HashMap<Integer, String> result = new HashMap<>(0);
        INVALID_LIST.forEach((key, item) -> item.forEach(value -> {
            if (this.pageInfo.contains(value)) {
                result.put(key, value);
            }
        }));
        if (result.size() <= 0) {
            result.put(GET_FILE_SUCCESS.getCode(), GET_FILE_SUCCESS.getMessage());
        }
        return result;
    }

    public FileInfoModel getFileWithPassword() throws IOException, URISyntaxException {
        String sign = PatternUtil.matchData("'sign':'(.*?)'", this.pageInfo);
        logger.info("sign: {}", sign);
        if (StringUtils.isEmpty(sign)) {
            // 目录
            String scriptData = PatternUtil.matchData("<script type=\"text/javascript\">(.*?)</script>", this.pageInfo.replace("\n", ""));
            String paramsData = PatternUtil.matchData("data:\\{(.*?)\\},", this.pageInfo.replace(" ", "").replace("\n", ""));
            logger.info("scriptData: {}, paramsData: {}", scriptData, paramsData);
            HashMap<String, String> params = new HashMap<>(0);
            params.put("lx", PatternUtil.matchData("'lx':(.*?),", paramsData));
            params.put("fid", PatternUtil.matchData("'fid':(.*?),", paramsData));
            params.put("uid", PatternUtil.matchData("'uid':'(.*?)',", paramsData));
            params.put("pg", PatternUtil.matchData(PatternUtil.matchData("'pg':(.*?),", paramsData) + "\\ =(.*?);", scriptData));
            params.put("rep", PatternUtil.matchData("'rep':'(.*?)',", paramsData));
            params.put("t", PatternUtil.matchData("var\\ " + PatternUtil.matchData("'t':(.*?),", paramsData) + "\\ =\\ '(.*?)';", scriptData));
            params.put("k", PatternUtil.matchData("var\\ " + PatternUtil.matchData("'k':(.*?),", paramsData) + "\\ =\\ '(.*?)';", scriptData));
            params.put("up", PatternUtil.matchData("'up':(.*?),", paramsData));
            params.put("ls", PatternUtil.matchData("'ls':(.*?),", paramsData));
            params.put("pwd", password);
            String getFolderResponse = HttpClientUtil.doPost(host + "/filemoreajax.php", getVerifyPasswordHeader(host), params);
            logger.info("params: {}, getFolderResponse: {}", params, getFolderResponse);
        } else {
            // 单文件
            HashMap<String, String> passwordData = new HashMap<>(0);
            passwordData.put("action", "downprocess");
            passwordData.put("sign", sign);
            passwordData.put("p", password);
            String verifyPasswordResponseData = HttpClientUtil.doPost(host + "/ajaxm.php", getVerifyPasswordHeader(host), passwordData);
            VerifyPasswordResp verifyPasswordData = GsonUtil.toBean(verifyPasswordResponseData, VerifyPasswordResp.class);
            logger.info("verifyPasswordResponseData: {}", verifyPasswordData);
            if (Objects.equals(verifyPasswordData.getZt(), 1)) {
                String filePath = verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath();
                logger.info("filePath: {}", filePath);
                FileInfoModel fileInfo = getFileInfo();
                BeanUtils.copyProperties(verifyPasswordData, fileInfo);
                fileInfo.setDownloadUrl(!Objects.isNull(verifyPasswordData.getDownloadHost()) && !Objects.isNull(verifyPasswordData.getDownloadPath()) ? verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath() : null);
                fileInfo.setRedirectUrl(!Objects.isNull(verifyPasswordData.getDownloadHost()) && !Objects.isNull(verifyPasswordData.getDownloadPath()) ? getRedirectUrl(verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath()) : null);
                return fileInfo;
            }
        }
        return null;
    }

    public FileInfoModel getFileInfo() throws IOException, URISyntaxException {
        FileInfoModel fileInfo = new FileInfoModel();
        fileInfo.setFileName(PatternUtil.matchData("<div class=\"md\">(.*?)<span class=\"mtt\">", this.pageInfo));
        fileInfo.setFileSize(PatternUtil.matchData("<span class=\"mtt\">\\((.*?)\\)</span>", this.pageInfo));
        String updateTime = PatternUtil.matchData("<span class=\"mt2\"></span>(.*?)<span class=\"mt2\">", this.pageInfo);
        if (Objects.isNull(updateTime)) {
            updateTime = PatternUtil.matchData("时间:<\\/span>(.*?)<span class=\"mt2\">", this.pageInfo);
        }
        fileInfo.setUpdateTime(updateTime);
        fileInfo.setAuthor(PatternUtil.matchData("发布者:<\\/span>(.*?)<span class=\"mt2\">", this.pageInfo));
        String down1 = PatternUtil.matchData("var\\ loaddown\\ =\\ '(.*?)';", this.pageInfo);
        String down2 = PatternUtil.matchData("var\\ downloads\\ =\\ '(.*?)';", this.pageInfo);
        fileInfo.setDownloadHost(PatternUtil.matchData("submit.href\\ =\\ '(.*?)'" + (!Objects.isNull(down1) ? "\\ \\+\\ loaddown" : !Objects.isNull(down2) ? "\\ \\+\\ downloads" : ""), this.pageInfo));
        fileInfo.setDownloadPath(!Objects.isNull(down1) ? down1 : down2);
        fileInfo.setDownloadUrl(!Objects.isNull(fileInfo.getDownloadHost()) && !Objects.isNull(fileInfo.getDownloadPath()) ? fileInfo.getDownloadHost() + fileInfo.getDownloadPath() : null);
        fileInfo.setRedirectUrl(!Objects.isNull(fileInfo.getDownloadHost()) && !Objects.isNull(fileInfo.getDownloadPath()) ? getRedirectUrl(fileInfo.getDownloadHost() + fileInfo.getDownloadPath()) : null);
        logger.info("fileInfo: {}", fileInfo);
        return fileInfo;
    }

    public String getRedirectUrl(String url) throws IOException, URISyntaxException {
        if (Objects.isNull(url)) {
            return null;
        }
        return HttpClientUtil.doGetRedirectLocation(url, getRedirectHeader(), new HashMap<>(0));
    }

}
