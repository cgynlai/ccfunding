package com.cyl.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
	
   @Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
	   
		builder.inMemoryAuthentication().withUser("Tom").password("123123").roles("ADMIN");
	}	

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
	           .anyRequest()       //any other requests must 
	           .authenticated()    //be authenticated
	           .and()
	           .csrf()           
	           .disable()
	           .formLogin()
	           .loginPage("/admin/to/login/page.html")
	           .loginProcessingUrl("/security/do/login.html")  //url to process login
	           .defaultSuccessUrl("/admin/to/main/page.html")  //place to go after login
	           .usernameParameter("loginAcct")    // form input name to be fed to request 
	           .passwordParameter("userPswd")
	           .and()
	           .logout()
	           .logoutUrl("/security/do/logout.html")
	           .logoutSuccessUrl("/admin/to/login/page.html")
	           ;
}
   
}
