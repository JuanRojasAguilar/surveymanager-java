package com.survey.subresponseOption.application;

import java.util.Optional;

import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class SearchSubresponseOptionByIdUseCase {
  private SubresponseOptionService subresponseOptionService;

  public SearchSubresponseOptionByIdUseCase(SubresponseOptionService subresponseOptionService) {
    this.subresponseOptionService = subresponseOptionService;
  }

  public Optional<SubresponseOption> execute(int id) {
    return this.subresponseOptionService.searchById(id);
  }
}
