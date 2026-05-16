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
