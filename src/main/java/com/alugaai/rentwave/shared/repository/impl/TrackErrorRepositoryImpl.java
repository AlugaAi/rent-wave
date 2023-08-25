package com.alugaai.rentwave.shared.repository.impl;

import com.alugaai.rentwave.lib.data.database.DatabaseGateway;
import com.alugaai.rentwave.lib.error.exception.RepositoryException;
import com.alugaai.rentwave.shared.model.TrackErrorModel;
import com.alugaai.rentwave.shared.repository.TrackErrorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
@PropertySource("classpath:query/track_error.properties")
public class TrackErrorRepositoryImpl implements TrackErrorRepository {

    @Value("${SPI.TRACK.ERROR}")
    private String saveQuery;

    private final DatabaseGateway databaseGateway;

    @Override
    public void saveTrackError(TrackErrorModel trackErrorModel) {
        try {
            databaseGateway.persist(saveQuery, trackErrorModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryException("can't persist track_error.");
        }
    }
}
