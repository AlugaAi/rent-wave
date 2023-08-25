package com.alugaai.rentwave.security.model;

import com.alugaai.rentwave.lib.data.model.DomainModel;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends DomainModel {

    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("access")
    private String access;

}
