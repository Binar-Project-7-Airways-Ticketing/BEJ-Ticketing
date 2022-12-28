package com.binar.bejticketing.config;

import com.binar.bejticketing.security.AuthEntryPointJwt;
import com.binar.bejticketing.security.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
    private static final String ADMIN = "ADMIN_ROLE";
    private static final String USER = "USER_ROLE";
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthEntryPointJwt unauthorizedHandler;
    @Override
    protected void configure (AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-ui/**",
                "/v3/api-docs/**");
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
            .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/signup","/api/auth/signin").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/passenger/**").permitAll()
                .antMatchers("/api/luggage/**").permitAll()
                .antMatchers("/api/seat/**").permitAll()
                .antMatchers("/api/flight/**").permitAll()
                .antMatchers("/api/booking/**").permitAll()
                .antMatchers("/api/airport/**").permitAll()
                .antMatchers("/api/ticket/**").permitAll()
                .antMatchers("/api/plane/**").permitAll()
                .antMatchers("/api/payment/**").permitAll()
                .antMatchers("/api/notification/create").permitAll()
                .antMatchers("/api/notification/update").permitAll()

                .antMatchers("/api/notification/delete/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/notification/user/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/notification/read/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/notification/get/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/history/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);

        return authProvider;

    }



}
