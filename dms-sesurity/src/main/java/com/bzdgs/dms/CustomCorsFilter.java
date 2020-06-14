package com.bzdgs.dms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @ClassName: CustomCorsFilter
 * @Description: 通用的客户过滤器
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
public class CustomCorsFilter extends CorsFilter {

	/**
	 * 构造方法
	 */
    public CustomCorsFilter() {
        super(configurationSource());
    }

    /**
     * URL基础资源配置方法
     * @return
     */
    private static UrlBasedCorsConfigurationSource configurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setMaxAge(36000L);
        config.setAllowedMethods(Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("admin");
        System.out.println(encode);
    }
}
