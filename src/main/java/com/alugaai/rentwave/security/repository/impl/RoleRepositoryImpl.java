package com.alugaai.rentwave.security.repository.impl;

import com.alugaai.rentwave.lib.data.database.DatabaseGateway;
import com.alugaai.rentwave.lib.error.exception.RepositoryException;
import com.alugaai.rentwave.security.model.RoleModel;
import com.alugaai.rentwave.security.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/security/role.properties")
public class RoleRepositoryImpl implements RoleRepository {

    @Value("${SPI.ROLE}")
    private String saveQuery;

    @Value("${SPS.ROLE.BY.ID}")
    private String findByIdQuery;

    @Value("${SPS.ROLE.BY.TITLE}")
    private String findByTitleQuery;

    private final DatabaseGateway databaseGateway;

    @Override
    public void save(RoleModel roleModel) {
        try {
            databaseGateway.persist(saveQuery, roleModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't persist role.");
        }
    }

    @Override
    public Optional<RoleModel> findById(Long id) {
        try {
            final var params = new HashMap<String, Object>();
            params.put("id", id);
            return databaseGateway.retrieve(findByIdQuery, params, BeanPropertyRowMapper.newInstance(RoleModel.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't retrieve role.");
        }
    }

    @Override
    public Optional<RoleModel> findByTitle(String title) {
        try {
            final var params = new HashMap<String, Object>();
            params.put("title", title);

            return databaseGateway.retrieve(findByTitleQuery, params, BeanPropertyRowMapper.newInstance(RoleModel.class));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't retrieve role.");
        }
    }

}
