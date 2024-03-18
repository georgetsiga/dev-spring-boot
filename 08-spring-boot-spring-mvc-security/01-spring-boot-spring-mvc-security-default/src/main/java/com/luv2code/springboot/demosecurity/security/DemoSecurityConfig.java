package com.luv2code.springboot.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import javax.swing.*;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                ).logout(LogoutConfigurer::permitAll)
                .exceptionHandling(exHandler -> exHandler.accessDeniedPage("/access-denied"));
        return httpSecurity.build();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager(DataSource datasource) {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}pass123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}pass123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}pass123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }*/
}
