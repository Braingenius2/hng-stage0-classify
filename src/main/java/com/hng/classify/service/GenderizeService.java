package com.hng.classify.service;

import java.time.Instant;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hng.classify.dto.ClassifyResponse;
import com.hng.classify.dto.ClassifyResponseData;
import com.hng.classify.dto.GenderizeApiResponse;

@Service
public class GenderizeService {

  private final RestTemplate restTemplate;

  public GenderizeService() {
    this.restTemplate = new RestTemplate();
  }

  public ClassifyResponse processName(String name) {
    String apiUrl = "https://api.genderize.io/?name=" + name;

    GenderizeApiResponse genderizeResponse = restTemplate.getForObject(apiUrl, GenderizeApiResponse.class);

    if (genderizeResponse == null || genderizeResponse.gender() == null || genderizeResponse.count() == 0) {
      throw new RuntimeException("No prediction available for the provided name");
    }

    boolean isConfident = genderizeResponse.probability() >= 0.7 && genderizeResponse.count() >= 100;

    ClassifyResponseData data = new ClassifyResponseData(
        genderizeResponse.name(),
        genderizeResponse.gender(),
        genderizeResponse.probability(),
        genderizeResponse.count(),
        isConfident,
        Instant.now());

    return new ClassifyResponse("success", data);
  }
}
