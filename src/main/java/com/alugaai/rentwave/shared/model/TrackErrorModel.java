package com.alugaai.rentwave.shared.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrackErrorModel {

    private String title;
    private String payload;
    private String message;

}
