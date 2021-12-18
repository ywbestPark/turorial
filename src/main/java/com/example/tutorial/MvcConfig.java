package com.example.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    private String multiPathPath;

    @Value("${spring.webservice.intro}")
    private String introPage;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 루트 (/) 로 접근 시 introPage로 이동하는 매핑 추가
        registry.addRedirectViewController("/", introPage);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("user-photos", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        //Path uploadDir = Paths.get(dirName);
        //String uploadPath = uploadDir.toFile().getAbsolutePath();
        //String uploadPath = multiPathPath;

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ multiPathPath + "/");
    }
}