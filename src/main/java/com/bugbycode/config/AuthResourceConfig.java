package com.bugbycode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class AuthResourceConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${spring.rule.oauth.clientId}")
	private String clientId;
	
	@Value("${spring.rule.oauth.clientSecret}")
	private String clientSecret;
	
	@Value("${spring.rule.oauth.checkTokenUrl}")
	private String checkTokenUrl;
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        tokenService.setCheckTokenEndpointUrl(checkTokenUrl);
        resources.tokenServices(tokenService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        //工单管理
        .antMatchers("/rule/update").hasRole("RULE_UPDATE") //修改工单权限
        .antMatchers("/rule/insert").hasRole("RULE_INSERT") //添加工单权限
        .antMatchers("/rule/delete").hasRole("RULE_DELETE")  //删除工单权限
        
        .antMatchers("/rule/query","/rule/queryById","/account/queryByAccount","/account/query")
        	.hasAnyRole("RULE_QUERY","RULE_UPDATE",
        		"RULE_INSERT","RULE_DELETE")
        
        .antMatchers("/account/insert","/account/delete")
        	.hasAnyRole("RULE_UPDATE", "RULE_INSERT","RULE_DELETE")
        	
        ;
    }
}
