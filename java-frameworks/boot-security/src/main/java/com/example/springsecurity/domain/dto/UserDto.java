package com.example.springsecurity.domain.dto;

import com.example.springsecurity.domain.AbstractEntity;
import com.example.springsecurity.domain.SysRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

    private Long userId;

    private String userName;

    private String encryptedPassword;

    private boolean enabled;

    private String sysRoles;
}
