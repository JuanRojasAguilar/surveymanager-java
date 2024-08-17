package com.survey.rol.application;

import java.util.Optional;

import com.survey.rol.domain.entity.Rol;
import com.survey.rol.domain.service.RolService;

public class SearchRolByIdUseCase {
  private RolService rolService;

  public SearchRolByIdUseCase(RolService rolService) {
    this.rolService = rolService;
  }

  public Optional<Rol> execute(int id) {
    return this.rolService.searchById(id);
  }
}
