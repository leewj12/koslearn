package com.kosmo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/images/**",
                        "/static/**",
                        "/courseimages/**",
                        "/coursefiles/**",
                        "/Instructorimages/**",
                        "/userimages/**",
                        "/homeimages/**",
                        "/notice/**",
                        "/communityupload/**",
                        "/fragments/**")
                .addResourceLocations(
                        "file:src/main/resources/courseimages/",
                        "file:src/main/resources/coursefiles/",
                        "file:src/main/resources/Instructorimages/",
                        "file:src/main/resources/userimages/",
                        "file:src/main/resources/notice/",
                        "file:src/main/resources/communityupload/",
                        "classpath:/images/",
                        "classpath:/static/",
                        "classpath:/courseimages/",
                        "classpath:/coursefiles/",
                        "classpath:/Instructorimages/",
                        "classpath:/userimages/",
                        "classpath:/homeimages/",
                        "classpath:/notice/",
                        "classpath:/communityupload/",
                        "classpath:/fragments/");
    }
}
