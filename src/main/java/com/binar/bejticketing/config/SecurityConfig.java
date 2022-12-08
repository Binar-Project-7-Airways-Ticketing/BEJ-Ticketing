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

        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/auth/signin"
                        ,"/api/auth/signup").permitAll()
                .antMatchers("/api/airport/name/**"
                        ,"/api/airport/city/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()

                //user role
                .antMatchers("/api/user/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/booking/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/history/**").hasAnyAuthority(USER)
                .antMatchers("/api/luggage/**").hasAnyAuthority(USER,ADMIN)
                .antMatchers("/api/payment/update/**").hasAnyAuthority(USER)
                .antMatchers("/api/passenger/create/passenger"
                        ,"/api/passenger/create/passengers"
                        ,"/api/passenger/edit/passenger"
                        ,"/api/passenger/edit/passenger/**"
                        ,"/api/passenger/delete/passenger/**").hasAnyAuthority(USER)
                .antMatchers("/api/seat/**").hasAnyAuthority(USER,ADMIN)

                //admin role
                .antMatchers("/api/airport/create"
                        ,"/api/airport/update,/api/airport/delete/**"
                        ,"/api/airport/upload/**").hasAnyAuthority(ADMIN)
                .antMatchers("/api/flight/create"
                        ,"/api/flight/update"
                        ,"/api/flight/delete/**").hasAnyAuthority(ADMIN)
                .antMatchers("/api/plane/create"
                        ,"/api/plane/update,/api/plane/delete/**"
                        ,"/api/plane/create/plane-details"
                        ,"/api/plane/update/plane-details"
                        ,"/api/plane/delete/plane-details/**").hasAnyAuthority(ADMIN)
//                .antMatchers("/api/user/role/save,/api/user/role/addToUser").hasAnyAuthority(ADMIN)
                .antMatchers("/api/payment/create").hasAnyAuthority(ADMIN)
                .antMatchers("/api/passenger/create/age-category"
                        ,"/api/passenger/edit/age-category"
                        ,"/api/passenger/delete/age-category/**").hasAnyAuthority(ADMIN)
                .anyRequest().authenticated()
            .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(),
                        UsernamePasswordAuthenticationFilter.class);

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
