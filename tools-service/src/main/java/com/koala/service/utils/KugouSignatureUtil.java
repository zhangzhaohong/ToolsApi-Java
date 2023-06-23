package com.koala.service.utils;

import com.koala.data.models.kugou.signature.KugouSignatureDataModel;
import com.koala.data.models.kugou.signature.KugouSignatureRespDataModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

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
@Component
public class KugouSignatureUtil {
    private static String host;

    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    @Value("${kugou.signature.host-1}")  //删除掉static
    public void setHost(String host) {
        KugouSignatureUtil.host = host;
    }

    public static KugouSignatureDataModel encrypt(String paramsData) throws IOException {
        Map<String, String> params = new HashMap<>(0);
        params.put("params", paramsData);
        String response = HttpClientUtil.doPostJson(host, GsonUtil.toString(params));
        if (!ObjectUtils.isEmpty(response)) {
            KugouSignatureRespDataModel respData = GsonUtil.toBean(response, KugouSignatureRespDataModel.class);
            if (!Objects.isNull(respData) && !Objects.isNull(respData.getData()) && !ObjectUtils.isEmpty(respData.getData().getSignature())) {
                return respData.getData();
            }
            return null;
        }
        return null;
    }
}
