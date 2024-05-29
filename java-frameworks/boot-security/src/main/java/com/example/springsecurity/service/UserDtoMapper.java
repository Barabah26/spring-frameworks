package com.example.springsecurity.service;

import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class UserDtoMapper extends DtoMapperFacade<SysUser, UserDto> {
    public UserDtoMapper() {
        super(SysUser.class, UserDto.class);
    }

    @Override
    protected void decorateDto(UserDto dto, SysUser entity) {
        String roles = entity.getSysRoles().stream().map(SysRole::getRoleName).collect(Collectors.joining(", "));
        dto.setSysRoles(roles);
    }
}
