package com.xiaoer.manage.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Shiroconfig {
	
	/*
	 * //设置加密方式
	 * 
	 * @Bean("hashedCredentialsMatcher") public HashedCredentialsMatcher
	 * hashedCredentialsMatcher() { HashedCredentialsMatcher credentialsMatcher =new
	 * HashedCredentialsMatcher(); credentialsMatcher.setHashAlgorithmName("MD5");
	 * credentialsMatcher.setHashIterations(1024);
	 * credentialsMatcher.setStoredCredentialsHexEncoded(true); return
	 * credentialsMatcher; }
	 */
	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securitymanager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securitymanager);
		Map<String,String> filterChainDefinitionMap =new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/logout","logout");
		filterChainDefinitionMap.put("/hello","anon");
		filterChainDefinitionMap.put("/","user");
		filterChainDefinitionMap.put("/**","authc");
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	};
	
	@Bean(name="defalutWebSecurityManager")
	public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("userRealm")MyShiroRealm userRealm) {
		DefaultWebSecurityManager defaultWebSecurityManger =new DefaultWebSecurityManager();
		defaultWebSecurityManger.setRealm(userRealm);
		defaultWebSecurityManger.setCacheManager(CacheManager()); //注入缓存对象
		defaultWebSecurityManger.setRememberMeManager(rememberMeManager());
		return defaultWebSecurityManger;
	}
	@Bean(name="ehCacheManager")
	public EhCacheManager CacheManager() {
		EhCacheManager cacheManager=new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return cacheManager;
	}
	@Bean(name="userRealm")
	public MyShiroRealm getUserRealm() {
		return new MyShiroRealm();
	}
	
	@Bean	//cookie对象
	public SimpleCookie rememberMe() {
	System.out.println("rememberMe()");
	SimpleCookie simplecookie =new SimpleCookie("rememberMe");
	simplecookie.setMaxAge(259200);
	return simplecookie;
	}
	@Bean //cookie管理对象
	public CookieRememberMeManager rememberMeManager() {
		System.out.println("rememberMeManager()");
		CookieRememberMeManager manager =new CookieRememberMeManager();
		manager.setCookie(rememberMe());
		return manager;
	}
}
