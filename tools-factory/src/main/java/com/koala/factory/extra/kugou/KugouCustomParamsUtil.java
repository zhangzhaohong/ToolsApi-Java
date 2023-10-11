package com.koala.factory.extra.kugou;

import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.GsonUtil;
import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.koala.service.data.redis.RedisKeyPrefix.KUGOU_COOKIE_TOKEN;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 10:48
 * @description
 */
@Component
public class KugouCustomParamsUtil {

    @Resource
    private ResourceLoader resourceLoader;

    @Resource(name = "RedisService")
    private RedisService redisService;

    public Map<String, Object> getKugouCustomParams() {
        try {
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:params/custom.kugou.params.txt");
            InputStream inputStream = resource.getInputStream();
            String custom = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.hasLength(custom)) {
                Map<String, Object> cookieMap = GsonUtil.toMaps(custom);
                String token = redisService.get(KUGOU_COOKIE_TOKEN);
                if (StringUtils.hasLength(token)) {
                    cookieMap.put("token", token);
                }
                return cookieMap;
            }
        } catch (Exception ignore) {
        }
        HashMap<String, Object> defaultHashMap = new HashMap<>();
        defaultHashMap.put("userId", "0");
        defaultHashMap.put("token", "");
        defaultHashMap.put("kg_cookie", "kg_mid=" + KugouMidGenerator.getMid());
        return defaultHashMap;
    }

}
