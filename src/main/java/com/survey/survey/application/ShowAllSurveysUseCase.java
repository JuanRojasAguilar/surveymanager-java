package com.survey.rol.application;

import java.util.List;
import java.util.Optional;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class ShowAllSurveysUseCase {
  private RolService rolService;

  public ShowAllSurveysUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public Optional<List<Rol>> execute() {
    return this.rolService.showAll();
  }
}
