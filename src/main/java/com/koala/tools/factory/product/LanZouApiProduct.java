package com.koala.tools.factory.product;

import com.koala.tools.models.file.FileInfoModel;
import com.koala.tools.models.lanzou.FolderDataRespModel;
import com.koala.tools.models.lanzou.FolderFileInfoRespModel;
import com.koala.tools.models.lanzou.VerifyPasswordRespModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.HttpClientUtil;
import com.koala.tools.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.koala.tools.enums.LanZouResponseEnums.GET_FILE_SUCCESS;
import static com.koala.tools.utils.HeaderUtil.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 16:08
 * @description
 */
public class LanZouApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(LanZouApiProduct.class);
    private String id;
    private String url;
    private String host;
    private String password;
    private String pageData;
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
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void getIdByUrl() {
        if (!StringUtils.isEmpty(this.url)) {
            String rule = "com/";
            this.id = this.url.substring(this.url.lastIndexOf(rule) + rule.length(), Objects.equals(this.url.lastIndexOf("/"), this.url.lastIndexOf(rule) + rule.length() - 1) ? this.url.length() : this.url.lastIndexOf("/"));
        }
    }

    public void initPageData() throws IOException, URISyntaxException {
        if (!StringUtils.isEmpty(this.id)) {
            this.pageData = getPageInfo(0, this.id, 1);
        }
    }

    private String getPageInfo(int index, String id, int mode) throws IOException, URISyntaxException {
        if (Objects.equals(index, HOST_LIST.size() - 1)) {
            if (Objects.equals(mode, 2)) {
                return null;
            } else {
                mode++;
                return getPageInfo(0, id, mode);
            }
        } else {
            String data = requestPageData(index, id, mode);
            if (StringUtils.isEmpty(data)) {
                index++;
                return getPageInfo(index, id, mode);
            } else {
                host = HOST_LIST.get(index);
                return data;
            }
        }
    }

    private String requestPageData(int index, String id, int mode) throws IOException, URISyntaxException {
        StringJoiner joiner = new StringJoiner("");
        joiner.add(HOST_LIST.get(index));
        logger.info("[LanZouApiProduct]({}) mode: {}", id, mode);
        if (Objects.equals(mode, 1)) {
            joiner.add("/tp/");
        } else {
            joiner.add("/");
        }
        joiner.add(id);
        String tmp = joiner.toString();
        logger.info("[LanZouApiProduct]({}) tmpFilePath: {}", id, tmp);
        return HttpClientUtil.doGet(tmp, getHeader(), new HashMap<>(0));
    }

    public Map<Integer, String> checkStatus() {
        HashMap<Integer, String> result = new HashMap<>(0);
        INVALID_LIST.forEach((key, item) -> {
            for (String value : item) {
                if (this.pageData.contains(value)) {
                    result.put(key, value);
                    break;
                }
            }
        });
        if (result.size() <= 0) {
            result.put(GET_FILE_SUCCESS.getCode(), GET_FILE_SUCCESS.getMessage());
        }
        return result;
    }

    private String getPageData(String id, int mode) throws IOException, URISyntaxException {
        if (mode < 0) {
            return null;
        }
        int index = 0;
        while (index <= HOST_LIST.size() - 1) {
            StringJoiner joiner = new StringJoiner("");
            joiner.add(HOST_LIST.get(index));
            logger.info("[LanZouApiProduct]({}) mode: {}", id, mode);
            if (Objects.equals(mode, 1)) {
                joiner.add("/tp/");
            } else {
                joiner.add("/");
            }
            joiner.add(id);
            String tmp = joiner.toString();
            logger.info("[LanZouApiProduct]({}) tmpFilePath: {}", id, tmp);
            String response = HttpClientUtil.doGet(tmp, getHeader(), new HashMap<>(0));
            if (!Objects.isNull(response)) {
                return response;
            }
            if (Objects.equals(index, HOST_LIST.size() - 1)) {
                mode--;
                return getPageData(id, mode);
            }
            index++;
        }
        return null;
    }

    public Object getFileWithPassword() throws IOException, URISyntaxException {
        String sign = PatternUtil.matchData("'sign':'(.*?)'", this.pageData);
        logger.info("[LanZouApiProduct]({}) sign: {}", id, sign);
        if (StringUtils.isEmpty(sign)) {
            // 目录
            String scriptData = PatternUtil.matchData("<script type=\"text/javascript\">(.*?)</script>", this.pageData.replace("\n", ""));
            String paramsData = PatternUtil.matchData("data:\\{(.*?)\\},", this.pageData.replace(" ", "").replace("\n", ""));
            logger.info("[LanZouApiProduct]({}) scriptData: {}, paramsData: {}", id, scriptData, paramsData);
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
            logger.info("[LanZouApiProduct]({}) params: {}, getFolderResponse: {}", id, params, getFolderResponse);
            if (!Objects.isNull(getFolderResponse)) {
                FolderDataRespModel folderData = GsonUtil.toBean(getFolderResponse, FolderDataRespModel.class);
                if (Objects.equals(folderData.getZt(), 1)) {
                    ArrayList<FileInfoModel> fileInfoList = new ArrayList<>(0);
                    Object folderFileData = folderData.getText();
                    if (folderFileData instanceof ArrayList) {
                        ((ArrayList<?>) folderFileData).forEach(item -> {
                            try {
                                String filePageInfo = getPageData(GsonUtil.toBean(GsonUtil.toString(item), FolderFileInfoRespModel.class).getId(), 1);
                                FileInfoModel fileInfo = getFileInfo(filePageInfo);
                                fileInfoList.add(fileInfo);
                            } catch (IOException | URISyntaxException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    return fileInfoList;
                } else {
                    return null;
                }
            }
        } else {
            // 单文件
            HashMap<String, String> passwordData = new HashMap<>(0);
            passwordData.put("action", "downprocess");
            passwordData.put("signs", "?ctdf");
            passwordData.put("sign", sign);
            passwordData.put("p", password);
            String verifyPasswordResponseData = HttpClientUtil.doPost(host + "/ajaxm.php", getVerifyPasswordHeader(host), passwordData);
            VerifyPasswordRespModel verifyPasswordData = GsonUtil.toBean(verifyPasswordResponseData, VerifyPasswordRespModel.class);
            logger.info("[LanZouApiProduct]({}) verifyPasswordResponseData: {}", id, verifyPasswordData);
            if (Objects.equals(verifyPasswordData.getZt(), 1)) {
                String filePath = verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath();
                logger.info("[LanZouApiProduct]({}) filePath: {}", id, filePath);
                FileInfoModel fileInfo = getFileInfo(null);
                BeanUtils.copyProperties(verifyPasswordData, fileInfo);
                fileInfo.setDownloadUrl(!Objects.isNull(verifyPasswordData.getDownloadHost()) && !Objects.isNull(verifyPasswordData.getDownloadPath()) ? verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath() : null);
                fileInfo.setRedirectUrl(!Objects.isNull(verifyPasswordData.getDownloadHost()) && !Objects.isNull(verifyPasswordData.getDownloadPath()) ? getRedirectUrl(verifyPasswordData.getDownloadHost() + "/file/" + verifyPasswordData.getDownloadPath()) : null);
                return fileInfo;
            }
        }
        return null;
    }

    public FileInfoModel getFileInfo(String pageData) throws IOException, URISyntaxException {
        String inputPageInfo = pageData;
        if (Objects.isNull(inputPageInfo)) {
            inputPageInfo = this.pageData;
        }
        FileInfoModel fileInfo = new FileInfoModel();
        fileInfo.setFileName(PatternUtil.matchData("<div class=\"md\">(.*?)<span class=\"mtt\">", inputPageInfo));
        fileInfo.setFileSize(PatternUtil.matchData("<span class=\"mtt\">\\((.*?)\\)</span>", inputPageInfo));
        String updateTime = PatternUtil.matchData("<span class=\"mt2\"></span>(.*?)<span class=\"mt2\">", inputPageInfo);
        if (Objects.isNull(updateTime)) {
            updateTime = PatternUtil.matchData("时间:<\\/span>(.*?)<span class=\"mt2\">", inputPageInfo);
        }
        fileInfo.setUpdateTime(updateTime);
        fileInfo.setAuthor(PatternUtil.matchData("发布者:<\\/span>(.*?)<span class=\"mt2\">", inputPageInfo));
        String down1 = PatternUtil.matchData("var\\ loaddown\\ =\\ '(.*?)';", inputPageInfo);
        String down2 = PatternUtil.matchData("var\\ downloads\\ =\\ '(.*?)';", inputPageInfo);
        fileInfo.setDownloadHost(PatternUtil.matchData("submit.href\\ =\\ '(.*?)'" + (!Objects.isNull(down1) ? "\\ \\+\\ loaddown" : !Objects.isNull(down2) ? "\\ \\+\\ downloads" : ""), inputPageInfo));
        fileInfo.setDownloadPath(!Objects.isNull(down1) ? down1 : down2);
        fileInfo.setDownloadUrl(!Objects.isNull(fileInfo.getDownloadHost()) && !Objects.isNull(fileInfo.getDownloadPath()) ? fileInfo.getDownloadHost() + fileInfo.getDownloadPath() : null);
        fileInfo.setRedirectUrl(!Objects.isNull(fileInfo.getDownloadHost()) && !Objects.isNull(fileInfo.getDownloadPath()) ? getRedirectUrl(fileInfo.getDownloadHost() + fileInfo.getDownloadPath()) : null);
        logger.info("[LanZouApiProduct]({}) fileInfo: {}", id, fileInfo);
        return fileInfo;
    }

    public String getRedirectUrl(String url) throws IOException, URISyntaxException {
        if (Objects.isNull(url)) {
            return null;
        }
        return HttpClientUtil.doGetRedirectLocation(url, getRedirectHeader(), new HashMap<>(0));
    }

    public String getPageData() {
        return pageData;
    }

    /**
     * 下面是打印log区域
     */
    public void printParams() {
        logger.info("[LanZouApiProduct]({}) inputParams: {url={}, password={}}", id, url, password);
    }

    public void printPageData() {
        logger.info("[LanZouApiProduct]({}) pageData: {}}", id, pageData);
    }

}
