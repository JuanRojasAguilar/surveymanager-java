package com.survey.responseOption.application;

import java.util.List;
import java.util.Optional;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;

public class ShowAllResponseOptionsUseCase {
  private ResponseOptionService responseOptionService;

  public ShowAllResponseOptionsUseCase(ResponseOptionService responseOptionService) {
    this.responseOptionService = responseOptionService;
  }

  public Optional<List<ResponseOption>> execute() {
    return this.responseOptionService.showAll();
  }
}
