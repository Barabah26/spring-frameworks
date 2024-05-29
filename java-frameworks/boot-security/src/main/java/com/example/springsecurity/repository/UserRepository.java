package com.example.springsecurity.repository;

import com.example.springsecurity.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findUsersByUserName(String userName);

}
