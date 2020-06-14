package com.bzdgs.dms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName: PasswordEncoderConfig
 * @Description: 密码转码配置对象
 * @author Chunjie He
 * @date 2017-09-01
 * @since 1.7
 */
@Configuration
public class PasswordEncoderConfig {
	
	/**
	 * 实例化密码转码器
	 * @return
	 */
    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
