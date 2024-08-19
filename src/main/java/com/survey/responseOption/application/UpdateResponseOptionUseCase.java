package com.survey.responseOption.application;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;

public class UpdateResponseOptionUseCase {
  private ResponseOptionService responseOptionService;

  public UpdateResponseOptionUseCase(ResponseOptionService responseOptionService) {
    this.responseOptionService = responseOptionService;
  }

  public void execute(ResponseOption responseOption) {
    this.responseOptionService.update(responseOption);
  }
}
