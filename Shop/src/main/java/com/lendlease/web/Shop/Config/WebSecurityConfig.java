package com.lendlease.web.Shop.Config;

import com.lendlease.web.Shop.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/products/add").hasAuthority("ADMIN")
                .antMatchers("/products/update/**").hasAuthority("ADMIN")
                .antMatchers("/products/delete/**").hasAuthority("ADMIN")
                .antMatchers("/cart/add/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers("/cart/update/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers("/cart/delete/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().defaultSuccessUrl("/product", true)
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

        http.cors().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
