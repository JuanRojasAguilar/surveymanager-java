package com.survey.responseOption.application;

import java.util.Optional;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;

public class SearchResponseOptionByIdUseCase {
  private ResponseOptionService responseOptionService;

  public SearchResponseOptionByIdUseCase(ResponseOptionService responseOptionService) {
    this.responseOptionService = responseOptionService;
  }

  public Optional<ResponseOption> execute(int id) {
    return this.responseOptionService.searchById(id);
  }
}
