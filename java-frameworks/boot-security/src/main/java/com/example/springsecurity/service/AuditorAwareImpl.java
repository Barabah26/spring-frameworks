package com.example.springsecurity.service;

import com.example.springsecurity.domain.SysUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<SysUser> {
    @Override
    public Optional<SysUser> getCurrentAuditor() {
        // Can use Spring Security to return currently logged in user
         return Optional.of(new SysUser(20L, "Aware", "enc", true, null));
//         return Optional.of((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
