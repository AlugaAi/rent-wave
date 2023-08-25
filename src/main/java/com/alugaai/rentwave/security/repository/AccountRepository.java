package com.alugaai.rentwave.security.repository;

import com.alugaai.rentwave.security.model.AccountModel;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    void save(AccountModel userModel);

    Optional<AccountModel> findById(UUID id);

    Optional<AccountModel> findByUsername(String username);

}
