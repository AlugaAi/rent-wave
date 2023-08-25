package com.alugaai.rentwave.security.repository.impl;

import com.alugaai.rentwave.lib.data.database.DatabaseGateway;
import com.alugaai.rentwave.lib.error.exception.RepositoryException;
import com.alugaai.rentwave.security.model.AccountModel;
import com.alugaai.rentwave.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/security/account.properties")
public class AccountRepositoryImpl implements AccountRepository {

    @Value("${SPI.USER}")
    private String saveQuery;

    @Value("${SPS.USER.BY.ID}")
    private String findByIdQuery;

    @Value("${SPS.USER.BY.USERNAME}")
    private String findByUsernameQuery;

    private final DatabaseGateway databaseGateway;

    @Override
    public void save(AccountModel userModel) {
        try {
            databaseGateway.persist(saveQuery, userModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't persist account.", userModel);
        }
    }

    @Override
    public Optional<AccountModel> findById(UUID id) {
        try {
            final var params = new HashMap<String, Object>();
            params.put("id", id);

            return databaseGateway.retrieve(findByIdQuery, params, BeanPropertyRowMapper.newInstance(AccountModel.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't retrieve account.");
        }
    }

    @Override
    public Optional<AccountModel> findByUsername(String username) {
        try {
            final var params = new HashMap<String, Object>();
            params.put("username", username);

            return databaseGateway.retrieve(findByUsernameQuery, params, BeanPropertyRowMapper.newInstance(AccountModel.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't retrieve account.");
        }
    }

}
