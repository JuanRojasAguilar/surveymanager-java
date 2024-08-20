package com.survey.subresponseOption.application;

import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class DeleteSubresponseOptionUseCase {
  private SubresponseOptionService subresponseOptionService;

  public DeleteSubresponseOptionUseCase(SubresponseOptionService subresponseOptionService) {
    this.subresponseOptionService = subresponseOptionService;
  }

  public boolean execute(int id) {
    return this.subresponseOptionService.delete(id);
  }
}
