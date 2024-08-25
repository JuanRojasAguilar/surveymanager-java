package com.survey.responseOption.infraestructure.ui;

import com.survey.catalog.application.SearchCatalogByIdUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;
import com.survey.catalog.infraestructure.repository.CatalogRepository;
import com.survey.responseOption.domain.entity.ResponseOption;

import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.text.MessageFormat;

public class UserFormResponseJRadioButton extends JRadioButton {
    private static final int FONT_SIZE = 12;
    private static final String FONT_NAME = "Arial";

    private final CatalogService catalogService;
    private final SearchCatalogByIdUseCase searchCatalogByIdUseCase;

    public UserFormResponseJRadioButton(ResponseOption response, ItemListener itemListener) {
        super();
        this.catalogService = new CatalogRepository();
        this.searchCatalogByIdUseCase = new SearchCatalogByIdUseCase(catalogService);

        initializeRadioButton(response, itemListener);
    }

    private void initializeRadioButton(ResponseOption response, ItemListener itemListener) {
        setText(getButtonText(response));
        setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
        addItemListener(itemListener);
    }

    private String getButtonText(ResponseOption response) {
        if (response.getIdCategoryCatalog() != 0) {
            return formatTextWithCatalog(response);
        } else {
            return response.getOptionText();
        }
    }

    private String formatTextWithCatalog(ResponseOption response) {
        Catalog catalog = searchCatalogByIdUseCase.execute(response.getIdCategoryCatalog()).get();
        return MessageFormat.format("{0} - {1}", catalog.getName(), response.getOptionText());
    }
}