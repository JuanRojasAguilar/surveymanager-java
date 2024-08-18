package com.survey.rol.application;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class UpdateRolUseCase {
  private RolService rolService;

  public UpdateRolUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public void execute(Rol rol) {
    this.rolService.update(rol);
  }
}
