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

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
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


        http.cors().configurationSource(request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of(request.getHeader("Origin")));
            cors.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setMaxAge(Duration.parse("3600"));
            return cors;
        }).and()
            .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/signup","/api/auth/signin","/film/getall","/api/**").permitAll()
                .antMatchers("/film/create","/film/update","/film/delete",
                        "/user/users","user/getall", "user/delete/{id}"
                        ,"/role/addToUser").hasRole("ADMIN")
                .antMatchers("user/update{id}").hasRole("USER")
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
