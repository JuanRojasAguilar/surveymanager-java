package com.survey.rol.application;

import com.survey.rol.domain.service.RolService;

public class DeleteRolUseCase {
  private RolService rolService;

  public DeleteRolUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public boolean execute(int id) {
    return this.rolService.delete(id);
  }
}
