package com.survey.responseOption.application;

import com.survey.responseOption.domain.service.ResponseOptionService;

public class DeleteResponseOptionUseCase {
  private ResponseOptionService responseOptionService;

  public DeleteResponseOptionUseCase(ResponseOptionService responseOptionService) {
    this.responseOptionService = responseOptionService;
  }

  public boolean execute(int id) {
    return this.responseOptionService.delete(id);
  }
}
