package com.alugaai.rentwave.shared.service.aop;

import com.alugaai.rentwave.lib.error.exception.ApplicationException;
import com.alugaai.rentwave.shared.annotations.TrackError;
import com.alugaai.rentwave.shared.model.TrackErrorModel;
import com.alugaai.rentwave.shared.repository.TrackErrorRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Aspect
@Configuration
@RequiredArgsConstructor
public class TrackErrorService {

    private final Gson gson;

    private final TrackErrorRepository trackErrorRepository;

    @AfterThrowing(value = "@annotation(com.alugaai.rentwave.shared.annotations.TrackError)", throwing = "ex")
    public void handleMethodError(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        TrackError annotation = signature.getMethod().getAnnotation(TrackError.class);

        System.out.println("UIP");
        System.out.println(ex);
        if (annotation != null) {
            Class<? extends Throwable>[] exceptionClasses = annotation.value();

            if (exceptionClasses.length == 0 || Arrays.asList(exceptionClasses).contains(ex.getClass())) {
                System.out.println("UEPA");
                if (ex == null) {
                    log.error("Caught null exception");
                } else {
                    log.error(ex.getMessage());

                if (ex instanceof ApplicationException applicationException) {
                    final var trackError = TrackErrorModel.builder()
                            .title(ex.getClass().getSimpleName())
                            .payload(applicationException.getError().getPayload())
                            .message(ex.getMessage())
                            .build();

                    trackErrorRepository.saveTrackError(trackError);
                } else {
                    final var trackError = TrackErrorModel.builder()
                            .title(ex.getClass().getSimpleName())
                            .payload(gson.toJson(ex.getMessage()))
                            .message(ex.getMessage())
                            .build();

                    trackErrorRepository.saveTrackError(trackError);
                }
                }
            }
        }
    }

}
