package com.alugaai.rentwave.lib.data.database.impl;

import com.alugaai.rentwave.lib.data.database.DatabaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DatabaseGatewayImpl implements DatabaseGateway {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void persist(final String query) {
        this.jdbcTemplate.update(query);
    }

    @Override
    public void persist(final String query, final Object object) {
        final var params = new BeanPropertySqlParameterSource(object);
        this.namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public void persist(final String query, final Map<String, Object> params) {
        this.namedParameterJdbcTemplate.update(query, params);
    }

    @Override
    public <T> T persist(final String query, final Object object, Class<T> idType) {
        final var params = new BeanPropertySqlParameterSource(object);
        final var keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(query, params, keyHolder);
        return retrieveId(keyHolder, idType);
    }

    @Override
    public <T> T persist(final String query, final Map<String, Object> params, Class<T> idType) {
        final var keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(query, (SqlParameterSource) params, keyHolder);
        return retrieveId(keyHolder, idType);
    }

    private <T> T retrieveId(final GeneratedKeyHolder keyHolder, Class<T> idType) {
        Object id = Objects.requireNonNull(keyHolder.getKeys()).get("id");

        if (Objects.nonNull(id)) {
            if (idType.isAssignableFrom(UUID.class)) {
                return idType.cast(UUID.fromString(id.toString()));
            } else if (idType.isAssignableFrom(Long.class)) {
                if (id instanceof Number) {
                    return idType.cast(((Number) id).longValue());
                }
            }
        }

        return null;
    }

    @Override
    public void update(final String query, final Object object) {
        persist(query, object);
    }

    @Override
    public <T> T update(final String query, final Object object, Class<T> idType) {
        return persist(query, object, idType);
    }

    @Override
    public void update(final String query, final Map<String, Object> params) {
        persist(query, params);
    }

    @Override
    public <T> T update(final String query, final Map<String, Object> params, Class<T> idType) {
        return persist(query, params, idType);
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final Class<T> requiredType) {
        try {
            return Optional.of(this.jdbcTemplate.queryForObject(query, requiredType));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final Map<String, Object> params, final Class<T> requiredType) {
        try {
            return Optional.of(this.namedParameterJdbcTemplate.queryForObject(query, params, requiredType));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> retrieve(final String query, final Map<String, Object> params, final RowMapper<T> rowMapper) {
        try {
            return Optional.of(this.namedParameterJdbcTemplate.queryForObject(query, params, rowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            return Optional.empty();
        }
    }

    @Override
    public <T> List<T> retrieveList(final String query, final Map<String, Object> params, final Class<T> requiredType) {
        try {
            return this.namedParameterJdbcTemplate.queryForList(query, params, requiredType);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public <T> List<T> retrieveList(final String query, final RowMapper<T> rowMapper) {
        return this.namedParameterJdbcTemplate.query(query, rowMapper);
    }

    @Override
    public <T> List<T> retrieveList(final String query, final Map<String, Object> params, final RowMapper<T> rowMapper) {
        return this.namedParameterJdbcTemplate.query(query, params, rowMapper);
    }

    private Long retrieveId(final GeneratedKeyHolder keuHolder) {
        final Map<String, Object> map = keuHolder.getKeys();
        final Object object = map.get("GENERATED_KEYS");
        return Objects.nonNull(object) ? keuHolder.getKey().longValue() : null;
    }

}
