package com.learn.motan.annotation;

import com.learn.motan.properties.BasicRefererConfigProperties;
import com.learn.motan.properties.BasicServiceConfigProperties;
import com.learn.motan.properties.ProtocolConfigProperties;
import com.learn.motan.properties.RegistryConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yf003 on 2017/8/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@EnableConfigurationProperties({BasicRefererConfigProperties.class,BasicServiceConfigProperties.class,
        ProtocolConfigProperties.class,RegistryConfigProperties.class})
public @interface MotanAutoConfig {

}
