package com.ivan.manage.configuration.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 方便静态引用application.properties的参数
 * Autowired annotation is not supported on static fields
 *
 * @author WB
 *
 */
@Configuration
public class BaseConfig implements ApplicationContextAware {

	private static Environment env;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		env = applicationContext.getBean(Environment.class);
	}
	public static String getValue(String key) {
		return env.getProperty(key);
	}

	/**
	 * 图片上传地址
	 *
	 * @return
	 */
	public static String getImageUploadDir() {
		return getValue("bomu.upload.url");
	}

	/**
	 * 图片预览地址
	 *
	 * @return
	 */
	public static String getImageVisitPath() {
		return getValue("bomu.upload.visit");
	}


	/**
	 * 代理注册地址
	 *
	 * @return
	 */
	public static String getAgentRegisterDomain() {
		return getValue("bomu.agent.domain");
	}


	/**
	 * 系统公告预览地址
	 *
	 * @return
	 */
	public static String getSystemPreviewUrl() {
		return getValue("bomu.noticeUrl");
	}

}
