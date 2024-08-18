package com.survey.catalog.application;

import java.util.List;
import java.util.Optional;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class ShowAllCatalogsUseCase {
  private CatalogService catalogService;

  public ShowAllCatalogsUseCase(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  public Optional<List<Catalog>> execute(int limit, int offset) {
    return this.catalogService.showAll(limit, offset);
  }
}
