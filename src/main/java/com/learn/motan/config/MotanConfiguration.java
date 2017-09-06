package com.learn.motan.config;

import com.learn.motan.annotation.MotanAutoConfig;
import com.learn.motan.lambdaInterface.Call;
import com.learn.motan.lambdaInterface.ConfigProperties;
import com.learn.motan.properties.BasicRefererConfigProperties;
import com.learn.motan.properties.BasicServiceConfigProperties;
import com.learn.motan.properties.ProtocolConfigProperties;
import com.learn.motan.properties.RegistryConfigProperties;
import com.learn.motan.support.BasicServiceConfigCondition;
import com.weibo.api.motan.config.ExtConfig;
import com.weibo.api.motan.config.springsupport.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * Created by yf003 on 2017/8/27.
 */
@MotanAutoConfig
public class MotanConfiguration implements ConfigProperties{

    /** 注册中心配置bean名称 */
    private static final String REGISTRY_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-registry_";
    /** 协议配置bean名称 */
    private static final String PROTOCOL_CONFIG_BEAN_NAME = "_spring-boot-starter-motan-protocol_";




    /**
     * define AnnotationBean
     */

    @Bean
    public AnnotationBean annotationBean(@Value("${motan.annotation.package}") String scanPackage) {
        AnnotationBean annotationBean = new AnnotationBean();
        if (!StringUtils.isEmpty(scanPackage)) {
            annotationBean.setPackage(scanPackage);
        }

        return annotationBean;
    }

    /**
     * define RegistryConfigBean
     */
    @Bean(name = REGISTRY_CONFIG_BEAN_NAME)
    public RegistryConfigBean registryConfig(RegistryConfigProperties registryConfig) {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setName(REGISTRY_CONFIG_BEAN_NAME);

        setProperties(registryConfig.getRegProtocol(),config::setRegProtocol);

        setProperties(registryConfig.getAddress(),config::setAddress);

        setProperties(registryConfig.getPort(),config::setPort);

        setProperties(registryConfig.getConnectTimeout(),config::setConnectTimeout);

        setProperties(registryConfig.getRequestTimeout(),config::setRequestTimeout);

        setProperties(registryConfig.getRegistrySessionTimeout(),config::setRegistrySessionTimeout);

        setProperties(registryConfig.getRegistryRetryPeriod(),config::setRegistryRetryPeriod);

        setProperties(registryConfig.getCheck(),config::setCheck);

        setProperties(registryConfig.getRegister(),config::setRegister);

        setProperties(registryConfig.getSubscribe(),config::setSubscribe);

        setProperties(registryConfig.getDefaultConfig(),config::setDefault);

        return config;
    }


    @Bean(name = PROTOCOL_CONFIG_BEAN_NAME)
    public ProtocolConfigBean protocolConfig(ProtocolConfigProperties protocolConfig) {
        ProtocolConfigBean config = new ProtocolConfigBean();

        // 如果未配置，则默认设置为motan
        if (!StringUtils.isEmpty(protocolConfig.getName())) {
            config.setName(protocolConfig.getName());
        } else {
            config.setName("motan");
        }


        setProperties(protocolConfig.getSerialization(),config::setSerialization);

        setProperties(protocolConfig.getIothreads(),config::setIothreads);

        setProperties(protocolConfig.getRequestTimeout(),config::setRequestTimeout);

        setProperties(protocolConfig.getRequestTimeout(),config::setRequestTimeout);

        setProperties(protocolConfig.getMinClientConnection(),config::setMinClientConnection);

        setProperties(protocolConfig.getMaxClientConnection(),config::setMaxClientConnection);

        setProperties(protocolConfig.getMinWorkerThread(),config::setMinWorkerThread);

        setProperties(protocolConfig.getMaxContentLength(),config::setMaxContentLength);

        setProperties(protocolConfig.getMaxServerConnection(),config::setMaxServerConnection);

        setProperties(protocolConfig.getPoolLifo(),config::setPoolLifo);

        setProperties(protocolConfig.getLazyInit(),config::setLazyInit);

        setProperties(protocolConfig.getEndpointFactory(),config::setEndpointFactory);

        setProperties(protocolConfig.getCluster(),config::setCluster);

        setProperties(protocolConfig.getLoadbalance(),config::setLoadbalance);

        setProperties(protocolConfig.getHaStrategy(),config::setHaStrategy);

        setProperties(protocolConfig.getWorkerQueueSize(),config::setWorkerQueueSize);

        setProperties(protocolConfig.getAcceptConnections(),config::setAcceptConnections);

        setProperties(protocolConfig.getProxy(),config::setProxy);

        setProperties(protocolConfig.getFilter(),config::setFilter);

        setProperties(protocolConfig.getRetries(),config::setRetries);

        setProperties(protocolConfig.getAsync(),config::setAsync);

        setProperties(protocolConfig.getDefaultConfig(),config::setDefault);

        return config;
    }


    @Bean
    @Conditional(BasicServiceConfigCondition.class)
    public BasicServiceConfigBean baseServiceConfig(BasicServiceConfigProperties basicServiceConfig) {
        BasicServiceConfigBean config = new BasicServiceConfigBean();

        if (!StringUtils.isEmpty(basicServiceConfig.getExport())) {
            config.setExport(basicServiceConfig.getExport());
        } else {
            // 未设置export，使用ProtocolConfigBeanName : port暴露
            if (StringUtils.isEmpty(basicServiceConfig.getExportPort())) {
                throw new RuntimeException("need service export port...");
            }
            config.setExport(PROTOCOL_CONFIG_BEAN_NAME + ":" + basicServiceConfig.getExportPort());
        }

        if (!StringUtils.isEmpty(basicServiceConfig.getRegistry())) {
            // 追加内部的注册配置bean
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicServiceConfig.getRegistry());
        } else {
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
        }


        if (!StringUtils.isEmpty(basicServiceConfig.getExtConfigId())) {
            ExtConfig extConfig = new ExtConfig();
            extConfig.setId(basicServiceConfig.getExtConfigId());
            config.setExtConfig(extConfig);
        }


        setProperties(basicServiceConfig.getProxy(),config::setProxy);

        setProperties(basicServiceConfig.getGroup(),config::setGroup);

        setProperties(basicServiceConfig.getVersion(),config::setVersion);

        setProperties(basicServiceConfig.getThrowException(),config::setThrowException);

        setProperties(basicServiceConfig.getApplication(),config::setApplication);

        setProperties(basicServiceConfig.getShareChannel(),config::setShareChannel);

        setProperties(basicServiceConfig.getAccessLog(),config::setAccessLog);

        setProperties(basicServiceConfig.getUsegz(),config::setUsegz);

        setProperties(basicServiceConfig.getMingzSize(),config::setMingzSize);

        setProperties(basicServiceConfig.getCodec(),config::setCodec);

        setProperties(basicServiceConfig.getFilter(),config::setFilter);

        setProperties(basicServiceConfig.getModule(),config::setModule);

        setProperties(basicServiceConfig.getActives(),config::setActives);

        setProperties(basicServiceConfig.getRegister(),config::setRegister);

        return config;
    }


    @Bean
    public BasicRefererConfigBean baseRefererConfig(BasicRefererConfigProperties basicRefererConfig) {
        BasicRefererConfigBean config = new BasicRefererConfigBean();

        config.setProtocol(PROTOCOL_CONFIG_BEAN_NAME);

        if (!StringUtils.isEmpty(basicRefererConfig.getRegistry())) {
            // 追加内部的注册配置bean
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME + "," + basicRefererConfig.getRegistry());
        } else {
            config.setRegistry(REGISTRY_CONFIG_BEAN_NAME);
        }

        if (!StringUtils.isEmpty(basicRefererConfig.getExtConfigId())) {
            ExtConfig extConfig = new ExtConfig();
            extConfig.setId(basicRefererConfig.getExtConfigId());
            config.setExtConfig(extConfig);
        }


        setProperties(basicRefererConfig.getGroup(),config::setGroup);

        setProperties(basicRefererConfig.getModule(),config::setModule);

        setProperties(basicRefererConfig.getApplication(),config::setApplication);

        setProperties(basicRefererConfig.getCheck(),config::setCheck);

        setProperties(basicRefererConfig.getAccessLog(),config::setAccessLog);

        setProperties(basicRefererConfig.getRetries(),config::setRetries);

        setProperties(basicRefererConfig.getThrowException(),config::setThrowException);

        setProperties(basicRefererConfig.getId(),config::setId);

        setProperties(basicRefererConfig.getVersion(),config::setVersion);

        setProperties(basicRefererConfig.getShareChannel(),config::setShareChannel);

        setProperties(basicRefererConfig.getRequestTimeout(),config::setRequestTimeout);

        setProperties(basicRefererConfig.getFilter(),config::setFilter);

        setProperties(basicRefererConfig.getActives(),config::setActives);

        setProperties(basicRefererConfig.getAsync(),config::setAsync);

        setProperties(basicRefererConfig.getCodec(),config::setCodec);

        setProperties(basicRefererConfig.getUsegz(),config::setUsegz);

        setProperties(basicRefererConfig.getMingzSize(),config::setMingzSize);

        setProperties(basicRefererConfig.getProxy(),config::setProxy);

        setProperties(basicRefererConfig.getMock(),config::setMock);


        // 文档未描述的4个属性暂不处理

        return config;
    }






    @Override
    public <T> void setProperties(T val, Call<T> call) {
        if(val != null && !StringUtils.isEmpty(val)){
            call.call(val);
        }
    }
}

