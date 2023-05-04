package com.koala.tools;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author koala
 */
@Slf4j
@MapperScan(basePackages = {"com.koala.tools.data.mapper"})
@SpringBootApplication
public class ToolsApplication {

    public static void main(String[] args) throws UnknownHostException {
        String dir = System.getProperty("user.dir");
        String folderDir = String.format("%s/ServiceData", dir);
        File folder = new File(folderDir);
        if (!folder.exists() && !folder.isDirectory()) {
            boolean status = folder.mkdirs();
            log.info(String.format("on create tmp path success: %s, status: %s", folderDir, status));
        }
        ConfigurableApplicationContext application = SpringApplication.run(ToolsApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String property = env.getProperty("server.servlet.context-path");
        String adminManagementUrl = env.getProperty("spring.boot.admin.client.url");
        String adminUser = env.getProperty("spring.boot.admin.client.username");
        String adminPassword = env.getProperty("spring.boot.admin.client.password");
        String path = property == null ? "" : property;
        log.info(
                "\n\t" +
                        "------------------------------------------------------------\n\t" +
                        "Application Tools is running! Access URLs:\n\t" +
                        "Local: \t\t\thttp://localhost:" + port + path + "/\n\t" +
                        "External: \t\thttp://" + ip + ":" + port + path + "/\n\t" +
                        "ServiceData: \t" + folderDir + "\n\t" +
                        "------------------------------------------------------------\n\t" +
                        "AdminManagementSystem:\n\t" +
                        "Local: \t\t" + adminManagementUrl + "/\n\t" +
                        "User: \t\t" + adminUser + "\n\t" +
                        "Password: \t" + adminPassword + "\n\t" +
                        "------------------------------------------------------------");
    }

    @RestController
    public static class DefaultController {
        @RequestMapping("/")
        public String welcome() {
            return "working in progress";
        }
    }

}
