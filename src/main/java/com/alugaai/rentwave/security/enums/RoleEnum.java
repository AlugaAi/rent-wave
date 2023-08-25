package com.alugaai.rentwave.security.enums;

import com.alugaai.rentwave.lib.error.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleEnum {

    USER (1L, "USER", "Normal user", "Can access only non administrative endpoints"),
    ADMIN (2L, "ADMIN", "Admin user", "Can access administrative endpoints");

    private Long id;
    private String title;
    private String description;
    private String access;

    public static RoleEnum parse(Long id) {
        return Arrays.stream(values()).filter(item -> item.id.equals(id))
                .findFirst().orElseThrow(() -> new ValidationException("can't parse role id."));
    }

}
