package com.empresax.security.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.empresax.security.web.security.jwt.JwtAuthorizationFilter;
import com.empresax.security.web.security.jwt.JwtUnAuthorizedEntryPoint;

// 1
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUnAuthorizedEntryPoint aUnAuthorizedEntryPoint;

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain filteChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/v1/**").permitAll()
                .antMatchers("/api/users/v1/add").permitAll()
                .antMatchers("/api/admin/user/v1/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(aUnAuthorizedEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    protected AuthenticationManager AuthenticationManager(HttpSecurity httpSecurity)
            throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

}
