package com.survey.rol.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.rol.domain.entity.Rol;

public interface RolService {
  void add(Rol rol);

  Optional<Rol> searchById(int id);

  Optional<List<Rol>> showAll();

  void update(Rol rol);

  boolean delete(int id);
}
