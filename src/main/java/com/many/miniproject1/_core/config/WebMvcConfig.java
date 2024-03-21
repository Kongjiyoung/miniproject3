package com.many.miniproject1._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration // IoC
public class WebMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/board/**", "/user/**")
//                .excludePathPatterns("/board/{id:\\d+}");
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:./images/")
                .setCachePeriod(60 * 60) // 초 단위 => 한시간
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
