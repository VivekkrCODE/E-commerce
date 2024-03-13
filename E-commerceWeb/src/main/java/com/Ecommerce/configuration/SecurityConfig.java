package com.Ecommerce.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Ecommerce.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration{
	@Autowired
	CustomUserDetailService customUserDetailService;
	@Autowired
	Google0Auth2SuccessHandler google0Auth2SuccessHandler;
protected void configure(HttpSecurity http) throws Exception{
	http
	    .authorizeRequests()
        .requestMatchers("/","/shop/**","/forgotPassword","/register","/h2-console/**").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .dispatcherTypeMatchers()
        .authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .failureUrl("/login?error=true")
        .defaultSuccessUrl("/")
        .usernameParameter("email")
        .passwordParameter("password")
        .and()
        .oauth2Login()
        .loginPage("/login")
        .successHandler(google0Auth2SuccessHandler)
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling()
        .and()
        .csrf()
        .disable();
	
	http.headers().frameOptions().disable();    
}
@Bean
public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
}
protected void configure(AuthenticationManagerBuilder auth ) throws Exception{
auth.userDetailsService(customUserDetailService);
}
protected void configure(WebSecurity web ) throws Exception{
web.ignoring().requestMatchers("/resources/**","/static/**","/images/**","/productimages/**","/css/**","/js/**");
}
}