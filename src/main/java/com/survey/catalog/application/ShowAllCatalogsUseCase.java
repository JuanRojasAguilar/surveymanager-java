package com.survey.rol.application;

import java.util.List;
import java.util.Optional;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class ShowAllRolesUseCase {
  private RolService rolService;

  public ShowAllRolesUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public Optional<List<Rol>> execute() {
    return this.rolService.showAll();
  }
}
