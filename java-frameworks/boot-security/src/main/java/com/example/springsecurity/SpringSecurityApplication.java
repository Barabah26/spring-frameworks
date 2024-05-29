package com.example.springsecurity;

import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
//@EnableWebSecurity
//@Import(WebSecurityDbFormConfig.class)
// For default comment UserDetails
public class SpringSecurityApplication implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("http://localhost:9000/h2-console");

/*        System.out.println(userRepository.findAll());
        System.out.println(passwordEncoder.encode("a"));
        String pass = "pass";
        String hashedPass = "jlsdfghsjhdfgh";
        if (passwordEncoder.matches(pass, hashedPass)) {
            System.out.println("Password correct");
        }*/
//        createUserAndRole();
    }

    private void createUserAndRole() {
        SysRole role = new SysRole(null, "USER", null);
        SysUser user = new SysUser(1L,
                "a1",
                passwordEncoder.encode("a1"),
                true,
                Set.of(role));
        role.setSysUser(user);
        System.out.println(user);
        userRepository.save(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
