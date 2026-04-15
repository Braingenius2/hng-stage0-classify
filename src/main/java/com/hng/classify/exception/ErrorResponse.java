package com.hng.classify.exception;

public record ErrorResponse(
    String status,
    String message) {

}
