package com.survey.rol.application;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class UpdateSurveyUseCase {
  private RolService rolService;

  public UpdateSurveyUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public void execute(Rol rol) {
    this.rolService.update(rol);
  }
}
