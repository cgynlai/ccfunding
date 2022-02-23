package com.cyl.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//put into container
	/* can't autowire in xxxxService due to different container, drop this idea in p247
	 * @Bean public BCryptPasswordEncoder getPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
   @Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
	   //  开启在内存中进行身份验证（开发时暂用）
	   //	builder.inMemoryAuthentication().withUser("Tom").password("123123").roles("ADMIN");
	   
	//正式 使用userDetailsService，即配置的数据库验证登录
	   builder
	   .userDetailsService(userDetailsService)
	   .passwordEncoder(passwordEncoder);
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
		       .antMatchers("/admin/get/page.html")
			   .hasRole("manager")
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
