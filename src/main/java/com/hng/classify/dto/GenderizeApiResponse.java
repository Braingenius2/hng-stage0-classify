package com.hng.classify.dto;

public record GenderizeApiResponse(
    String name,
    String gender,
    Double probability,
    int count) {

}
