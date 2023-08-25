package com.alugaai.rentwave.security.repository;

import com.alugaai.rentwave.security.model.RoleModel;

import java.util.Optional;

public interface RoleRepository {

    void save(RoleModel roleModel);

    Optional<RoleModel> findById(Long id);

    Optional<RoleModel> findByTitle(String title);

}
