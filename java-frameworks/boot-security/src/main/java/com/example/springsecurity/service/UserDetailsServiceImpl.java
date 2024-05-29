package com.example.springsecurity.service;

import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SysUser> sysUser = userRepository.findUsersByUserName(username);
        if (sysUser.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = sysUser.get().getSysRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .toList();
        return new User(sysUser.get().getUserName(), sysUser.get().getEncryptedPassword(), authorities);
//        return User.withUsername("a").password(passwordEncoder.encode("a")).authorities("USER").build();
    }

    public boolean createUser(String login, String password){
        Optional<SysUser> sysUser = userRepository.findUsersByUserName(login);
        if (sysUser.isPresent()) {
            throw new RuntimeException("User is present with username: " + login);
        }

        SysUser user = userRepository
                .save(new SysUser(null, login, passwordEncoder.encode(password), true, null));

        return true;
    }

//    public boolean createUser(String login,String password){
//        Optional<SysUser> sysUser = userRepository.findUsersByUserName(login);
//        if (sysUser.isPresent()){
//            throw new RuntimeException("User is already exist.");
//        }
//        SysUser user = userRepository
//                .save(new SysUser(null, login, passwordEncoder.encode(password), true, null));
//        return true;
//    }
    public List<SysUser> findAll() {
        return userRepository.findAll();
    }


}
