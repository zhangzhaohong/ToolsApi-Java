package com.koala.service.custom.http.wrapper;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 13:57
 * @description
 */
public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] body;

    public CustomHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = StreamUtils.copyToByteArray(request.getInputStream());
    }

    public String getBody() {
        return new String(body, StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
