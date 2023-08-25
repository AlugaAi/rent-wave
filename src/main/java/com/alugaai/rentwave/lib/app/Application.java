package com.alugaai.rentwave.lib.app;

import com.alugaai.rentwave.config.utils.ApplicationConstants;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Slf4j
public abstract class Application {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ApplicationConstants.DEFAULT_TIMEZONE));
        log.info("Actual hour :: {}", LocalDateTime.now());
    }

}
