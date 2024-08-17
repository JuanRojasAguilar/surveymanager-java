package com.survey.rol.application;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class AddRolUseCase {
  private RolService rolService;

  public AddRolUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public void execute(Rol rol) {
    this.rolService.add(rol);
  }
}
