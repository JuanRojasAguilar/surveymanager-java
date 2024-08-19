package com.survey.responseOption.application;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;

public class AddResponseOptionUseCase {
  private ResponseOptionService responseOptionService;

  public AddResponseOptionUseCase(ResponseOptionService responseOptionService) {
    this.responseOptionService = responseOptionService;
  }

  public void execute(ResponseOption responseOption) {
    this.responseOptionService.add(responseOption);
  }
}
