package com.xw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("doc.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:./files/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(md5TokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/plat/index/getPersonInfo","/plat/index/adoptList","/plat/index/crousel","/plat/index/recommendedPets","/plat/index/petDisplay","/plat/index/petCategory","/plat/goods/page")
                .excludePathPatterns("/web/login","/web/register","/file/upload","/role/selectAll","/web/updatePassword")
                .excludePathPatterns("/file/**","/favicon.ico","/swagger**/**","/webjars/**","/precache-manifest.**.js", "/v3/**","/service-worker.js","/doc.html");
        super.addInterceptors(registry);
    }

    @Bean
    public Md5TokenInterceptor md5TokenInterceptor() {
        return new Md5TokenInterceptor();
    }
}
