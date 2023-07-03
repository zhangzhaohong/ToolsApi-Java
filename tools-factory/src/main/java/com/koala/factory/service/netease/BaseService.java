package com.koala.factory.service.netease;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface BaseService {
    ResponseEntity<String> doRequest(HttpServletRequest request);
}
