package com.alugaai.rentwave.security.mapper;

import com.alugaai.rentwave.security.entity.Role;
import com.alugaai.rentwave.security.model.RoleModel;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toRole(RoleModel roleModel) {
        return Role.builder()
                .id(roleModel.getId())
                .title(roleModel.getTitle())
                .description(roleModel.getDescription())
                .build();
    }
}
