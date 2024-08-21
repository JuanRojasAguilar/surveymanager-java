package com.survey.subresponseOption.application;

import java.util.List;
import java.util.Optional;

import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;

public class ShowAllSubresponseOptionsUseCase {
  private SubresponseOptionService subresponseOptionService;

  public ShowAllSubresponseOptionsUseCase(SubresponseOptionService subresponseOptionService) {
    this.subresponseOptionService = subresponseOptionService;
  }

  public Optional<List<SubresponseOption>> execute() {
    return this.subresponseOptionService.showAll();
  }
}
