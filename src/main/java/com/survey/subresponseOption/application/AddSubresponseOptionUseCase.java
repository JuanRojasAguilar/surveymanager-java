package com.survey.subresponseOption.application;

import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class AddSubresponseOptionUseCase {
  private SubresponseOptionService subresponseOptionService;

  public AddSubresponseOptionUseCase(SubresponseOptionService subresponseOptionService) {
    this.subresponseOptionService = subresponseOptionService;
  }

  public void execute(SubresponseOption subresponseOption) {
    this.subresponseOptionService.add(subresponseOption);
  }
}
