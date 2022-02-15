package com.cyl.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
protected void configure(HttpSecurity security) throws Exception {
	
	security
	   .authorizeRequests()
	   .antMatchers("/admin/to/login/page.html")
	   .permitAll()
	   .antMatchers("/bootstrap/**")
			   .permitAll()
			   .antMatchers("/crowd/**")
			   .permitAll()
			   .antMatchers("/css/**")
			   .permitAll()
			   .antMatchers("/fonts/**")
			   .permitAll()
			   .antMatchers("/img/**")
			   .permitAll()
			   .antMatchers("/jquery/**")
			   .permitAll()
			   .antMatchers("/layer/**")
			   .permitAll()
			   .antMatchers("/script/**")
			   .permitAll()
			   .antMatchers("/ztree/**")
			   .permitAll()
	   .anyRequest()
	   .authenticated();
}
   
}
