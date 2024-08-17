package com.survey.catalog.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.catalog.domain.entity.Catalog;

public interface CatalogService {
  void add(Catalog catalog);

  Optional<Catalog> searchById(int id);

  Optional<List<Catalog>> showAll();

  void update(Catalog catalog);

  boolean delete(int id);
}
