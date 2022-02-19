package com.koala.tools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 23:17
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "basic-config")
public class BasicConfigProperties {
    private String host;
}
