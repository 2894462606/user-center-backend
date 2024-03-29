package com.pikachu.usercenter.config;

import com.pikachu.usercenter.interceptor.AuthInterceptor;
import com.pikachu.usercenter.interceptor.LoginInterceptor;
import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Web应用配置
 *
 * @author 笨蛋皮卡丘
 * @version 1.0
 */
@SpringBootConfiguration
@ConfigurationProperties(prefix = "web-config")
@Data
public class WebConfiguration implements WebMvcConfigurer {
    private String[] ExcludePathLogin;
    private String[] ExcludePathAuth;
    private String[] CorsOrigins;


    /**
     * 鉴权拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**", "/admin/**", "/team/**")
                .excludePathPatterns(ExcludePathLogin);
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns(ExcludePathAuth);
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     // 设置允许跨域的路径
    //     registry.addMapping("/**")
    //             .allowCredentials(true)
    //             // 设置允许跨域请求的域名
    //             // 当Credentials为true时，Origin不能为星号，需为具体的ip地址【如果接口不带cookie,ip无需设成具体ip】
    //             .allowedOriginPatterns(CORS_ORIGINS)
    //             // 是否允许证书 不再默认开启
    //             // 允许任意响应头
    //             .allowedHeaders("*")
    //             .exposedHeaders("*")
    //             // 设置允许的方法
    //             .allowedMethods("*");
    // }

    /**
     * 跨域过滤器配置
     */
    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));
        System.out.println(Arrays.toString(CorsOrigins));
        corsConfiguration.setAllowedOriginPatterns(List.of(CorsOrigins));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
