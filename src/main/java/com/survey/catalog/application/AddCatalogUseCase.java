package com.survey.catalog.application;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class AddCatalogUseCase {
  private CatalogService catalogService;

  public AddCatalogUseCase(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  public void execute(Catalog catalog) {
    this.catalogService.add(catalog);
  }
}
