package com.alugaai.rentwave.security.mapper;

import com.alugaai.rentwave.security.entity.Account;
import com.alugaai.rentwave.security.enums.RoleEnum;
import com.alugaai.rentwave.security.model.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toUser(AccountModel userModel) {
        return Account.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .role(RoleEnum.USER)
                .build();
    }

}
