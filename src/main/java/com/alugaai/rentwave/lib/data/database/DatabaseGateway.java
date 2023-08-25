package com.alugaai.rentwave.lib.data.database;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DatabaseGateway {

    void persist(final String query);
    void persist(final String query, Object object);
    void persist(final String query, Map<String, Object> params);
    <T> T persist(final String query, final Object object, Class<T> idType);
    <T> T persist(final String query, final Map<String, Object> params, Class<T> idType);
    void update(final String query, final Object object);
    <T> T update(final String query, final Object object, Class<T> idType);
    void update(final String query, final Map<String, Object> params);
    <T> T update(final String query, final Map<String, Object> params, Class<T> idType);
    <T> Optional<T> retrieve(final String query, final Class<T> requiredType);
    <T> Optional<T> retrieve(final String query, final Map<String, Object> params, final Class<T> requiredType);
    <T> Optional<T> retrieve(final String query, final Map<String, Object> params, final RowMapper<T> rowMapper);
    <T> List<T> retrieveList(final String query, final Map<String, Object> params, final Class<T> requiredType);
    <T> List<T> retrieveList(final String query, final RowMapper<T> rowMapper);
    <T> List<T> retrieveList(final String query, final Map<String, Object> params, final RowMapper<T> rowMapper);

}
