package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //Add support for JDBC --- no more hardcoded users
   @Bean
   public UserDetailsManager userDetailsManager(DataSource dataSource) {
       JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

       //Define query to retrieve a user by username
       jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

       //Define query to retrieve the authorities/roles by username
       jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");
       return jdbcUserDetailsManager;
   }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configure ->
                configure
                        .requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/employees/**").hasRole("ADMIN")
                );

        //Use HTTP Basic Auth
        httpSecurity.httpBasic(Customizer.withDefaults());

        //Disable Cross Site Request Forgery (CFR)
        //In general, not required for stateless REST APIs that use POST, PUT, DELETE and /or PATCH
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

    /* @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEES")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEES", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEES", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }*/
}
