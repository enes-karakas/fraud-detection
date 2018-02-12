package be.kdg.fraudedetection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*  http.requestMatcher(request -> {
            final String url = request.getServletPath() + request.getPathInfo();
            return !(url.startsWith("/api/"));
        });*/

        http.authorizeRequests()
                .antMatchers("/topic/**", "/register.do").permitAll()
      /*          .antMatchers("/client/**").access("hasRole('ROLE_CLIENT')")
                .antMatchers("/admin/**").access("hasRole('ROLE_REPAIRER')")*/
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
