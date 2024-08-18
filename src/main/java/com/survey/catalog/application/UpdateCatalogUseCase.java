package com.survey.catalog.application;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class UpdateCatalogUseCase {
  private CatalogService catalogService;

  public UpdateCatalogUseCase(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  public void execute(Catalog catalog) {
    this.catalogService.update(catalog);
  }
}
