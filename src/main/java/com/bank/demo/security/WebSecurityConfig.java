package com.bank.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Description of file WebSecurityConfig.java
 *  It is used to set the web security configuration
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Memory Authentication
		
		  auth.inMemoryAuthentication().withUser("admin").password("{noop}secret")
		  .roles("ADMIN", "USER");
		  auth.inMemoryAuthentication().withUser("user").password("{noop}user")
		  .roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/home", "/getAccount").hasRole("USER");
		http.authorizeRequests().antMatchers("/saveAccountOperation").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");

	}

}
