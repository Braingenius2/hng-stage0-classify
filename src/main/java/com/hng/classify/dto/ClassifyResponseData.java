package com.hng.classify.dto;

import java.time.Instant;

public record ClassifyResponseData(
    String name,
    String gender,
    Double probability,
    int sample_size,
    boolean is_confident,
    Instant processed_at) {

}
