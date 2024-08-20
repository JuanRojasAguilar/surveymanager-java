package com.survey.subresponseOption.application;

import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class UpdateSubresponseOptionUseCase {
  private SubresponseOptionService subresponseOptionService;

  public UpdateSubresponseOptionUseCase(SubresponseOptionService subresponseOptionService) {
    this.subresponseOptionService = subresponseOptionService;
  }

  public void execute(SubresponseOption subresponseOption) {
    this.subresponseOptionService.update(subresponseOption);
  }
}
