package com.example.tutorial;

import com.example.tutorial.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
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
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ multiPathPath + "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/*") // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                .excludePathPatterns("/loginForm"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
}
