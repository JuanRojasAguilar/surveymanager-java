package com.survey.catalog.application;

import java.util.Optional;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class SearchCatalogByIdUseCase {
  private CatalogService catalogService;

  public SearchCatalogByIdUseCase(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  public Optional<Catalog> execute(int id) {
    return this.catalogService.searchById(id);
  }
}
