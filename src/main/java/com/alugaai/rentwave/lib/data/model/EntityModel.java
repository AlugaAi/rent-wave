package com.alugaai.rentwave.lib.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class EntityModel {

    @Id
    protected UUID id;

    @Column("created_at")
    protected LocalDateTime createdAt;

    @Column("updated_at")
    protected LocalDateTime updatedAt;

}

