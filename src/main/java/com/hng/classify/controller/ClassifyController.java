package com.hng.classify.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hng.classify.dto.ClassifyResponse;
import com.hng.classify.service.GenderizeService;

@RestController
public class ClassifyController {

  private final GenderizeService genderizeService;

  public ClassifyController(GenderizeService genderizeService) {
    this.genderizeService = genderizeService;
  }

  @GetMapping("/api/classify")
  public ClassifyResponse classifyName(@RequestParam String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new RuntimeException("The 'name' parameter cannot be empty");
    }
    return genderizeService.processName(name);
  }

}
