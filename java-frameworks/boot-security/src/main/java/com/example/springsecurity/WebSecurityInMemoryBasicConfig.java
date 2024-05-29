package com.example.springsecurity;

import com.example.springsecurity.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class WebSecurityInMemoryBasicConfig  {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/h2-console/**", "/swagger").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(f -> f
                    .failureForwardUrl("/index")
                    .loginPage("/login")
                    .successForwardUrl("/dashboard")
                    .permitAll())
//                .and()
//                    .httpBasic(b -> b.realmName("name"))
//                .and()
                    .logout(l -> l// default url /logout
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://BADNAME@localhost:9000/login")
                        .permitAll())
//                .authenticationEntryPoint(authenticationEntryPoint)
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User
                .withUsername("a")
                .password(passwordEncoder.encode("a"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
/*    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("u")
                        .password("p")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/

}
