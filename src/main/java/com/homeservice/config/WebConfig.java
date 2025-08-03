package com.homeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("webapp/WEB-INF/jsp/", ".jsp");
    }
}
