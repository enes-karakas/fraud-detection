package be.kdg.fraudedetection.config;

import be.kdg.fraudedetection.bl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity
public class SecurityConfig  {

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.
                userDetailsService(userService).
                passwordEncoder(passwordEncoder);
    }

    @Configuration
//    @Order(1)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
           /* http.requestMatcher(request -> {
                final String url = request.getServletPath() + request.getPathInfo();
                return !(url.startsWith("/api/"));
            });*/

            http.authorizeRequests()
                    .antMatchers("/about.html", "/contact.html", "/index.html").permitAll()
//                    .antMatchers("/client/**").access("hasRole('ROLE_CLIENT')")
//                    .antMatchers("/repairer/**").access("hasRole('ROLE_REPAIRER')")
                    .anyRequest().authenticated()      // remaining URL's require authentication
                    .and()
                    .formLogin()
                    .loginPage("/login.do")
                    .failureUrl("/login-error")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
      *//*  http.requestMatcher(request -> {
            final String url = request.getServletPath() + request.getPathInfo();
            return !(url.startsWith("/api/"));
        });*//*

        http.authorizeRequests()
                .antMatchers("/topic/**", "/register.do").permitAll()
      *//*          .antMatchers("/client/**").access("hasRole('ROLE_CLIENT')")
                .antMatchers("/admin/**").access("hasRole('ROLE_REPAIRER')")*//*
                .anyRequest().authenticated()      // remaining URL's require authentication
                .and()
                .formLogin()
                .loginPage("/login.do")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .csrf().disable();
    }*/
}
