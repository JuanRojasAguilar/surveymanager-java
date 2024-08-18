package com.survey.catalog.application;

import com.survey.catalog.domain.service.CatalogService;

public class DeleteCatalogUseCase {
  private CatalogService catalogService;

  public DeleteCatalogUseCase(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  public boolean execute(int id) {
    return this.catalogService.delete(id);
  }
}
