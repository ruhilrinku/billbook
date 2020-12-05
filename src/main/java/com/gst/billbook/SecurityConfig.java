package com.gst.billbook;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;*/

@Configuration
public class SecurityConfig //extends WebSecurityConfigurerAdapter
{
	
	/*
	 * @Autowired DataSource dataSource;
	 * 
	 * // Enable jdbc authentication
	 * 
	 * @Autowired public void configAuthentication(AuthenticationManagerBuilder
	 * auth) throws Exception {
	 * auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(
	 * dataSource); }
	 * 
	 * @Bean public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception
	 * { JdbcUserDetailsManager jdbcUserDetailsManager = new
	 * JdbcUserDetailsManager(); jdbcUserDetailsManager.setDataSource(dataSource);
	 * return jdbcUserDetailsManager; }
	 * 
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 * 
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests() .antMatchers("/register").permitAll()
	 * .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll
	 * () .and() .logout() .permitAll();
	 * 
	 * http.csrf().disable(); }
	 */}