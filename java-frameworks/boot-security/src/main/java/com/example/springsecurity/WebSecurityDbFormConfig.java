package com.example.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityDbFormConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers("/css/**", "/js/**", "/login", "/base", "/registration", "/name", "/new", "/create").permitAll()
//                                .requestMatchers("/dashboard", "/home").hasRole("USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                    .successForwardUrl("/dashboard"))
//                .and()
                  .rememberMe(me -> me
                    .tokenValiditySeconds(86400)) // 24h // 7d default
//                .and()
                  .logout(l -> l
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID")
                    .permitAll());
        return http.build();
    }
}
