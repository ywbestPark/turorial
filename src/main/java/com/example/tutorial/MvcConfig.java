package com.example.tutorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    private String multiPathPath;

    @Value("${spring.webservice.intro}")
    private String introPage;

    @Override public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver mustacheViewResolver = new MustacheViewResolver();
        mustacheViewResolver.setCharset("UTF-8");
        mustacheViewResolver.setContentType("text/html; charset=UTF-8");
        mustacheViewResolver.setPrefix("classpath:/templates/"); // Prefix 설정
        //mustacheViewResolver.setSuffix(".html"); // Suffix 설정
        registry.viewResolver(mustacheViewResolver);// 위에서 생성한 Mustache 리졸버를 적용
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