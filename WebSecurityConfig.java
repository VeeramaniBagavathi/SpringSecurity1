package com.veera.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * @author Veeramani Bagavathi
 *         Since 11/Jul/2017
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                        .headers()
                        .and()
                        .exceptionHandling()
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                        .and()
                        .authorizeRequests()
                        .antMatchers("/ata/**").hasRole("ATA")
                        .antMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ATA")
                        .antMatchers("/admin/**").hasAnyRole("ADMIN", "ADMIN_VIEW")
                        .and()
                        .sessionManagement()
                        .invalidSessionUrl("/index.html")
                        .and()
                        .jee()
                        .authenticatedUserDetailsService(new PreAuthenticatedUserDetailsService())
                        .mappableRoles("EMPLOYEE", "ATA", "ADMIN", "ADMIN_VIEW", "ANONYMOUS")
                        .and()
                        .csrf()
                        .disable()
                        .logout()
                        .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                        .ignoring().antMatchers("/admin/static/**")
                        .and()
                        .ignoring().antMatchers("/static/**")
                        .and()
                        .ignoring().antMatchers("/ata/static/**")
                        .and()
                        .ignoring().antMatchers("/employee/static/**")
                        .and()
                        .ignoring().antMatchers("/geo/**")
                        .and()
                        .ignoring().antMatchers("/hostMonitor");
    }
}

