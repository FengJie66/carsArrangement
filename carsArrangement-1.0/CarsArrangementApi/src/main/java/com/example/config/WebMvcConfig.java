package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${api.imgFolderConfig}")
    private String imgFolderConfig;
    @Value("${api.imgMapConfig}")
    private String imgMapConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //本地图片映射
        registry.addResourceHandler(imgMapConfig).addResourceLocations(imgFolderConfig);
    }

    @Override  //跨域
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
