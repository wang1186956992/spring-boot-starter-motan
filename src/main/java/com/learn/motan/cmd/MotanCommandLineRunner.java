/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.learn.motan.cmd;


import com.learn.motan.properties.RegistryConfigProperties;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * MotanCommandLineRunner
 * 
 * @author 	alanwei
 * @since 	2016-09-11
 */
@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
@EnableConfigurationProperties({RegistryConfigProperties.class})
public class MotanCommandLineRunner implements CommandLineRunner {

	/** Registry Config */
	@Resource
	private RegistryConfigProperties registryConfig;
	
	@Override
	public void run(String... args) throws Exception {
		
		if (!registryConfig.getRegProtocol().toLowerCase().equals("local")) {
			// 开启注册中心
			MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
		}
	}

}
