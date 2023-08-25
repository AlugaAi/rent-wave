package com.alugaai.rentwave.security.model;

import com.alugaai.rentwave.lib.data.model.EntityModel;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
public class AccountModel extends EntityModel {

    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("role_id")
    private Long roleId;

}
