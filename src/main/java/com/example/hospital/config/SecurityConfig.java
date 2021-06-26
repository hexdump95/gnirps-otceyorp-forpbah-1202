package com.example.hospital.config;

import com.example.hospital.filters.JwtAuthenticationFilter;
import com.example.hospital.filters.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(authorizationFilter(), BasicAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter =
                new JwtAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/api/auth/login");
        return filter;
    }

    @Bean
    public BasicAuthenticationFilter authorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/swagger-config",
                "/v3/api-docs",
                "/v3/api-docs.yaml",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-ui*/**");
    }

}
