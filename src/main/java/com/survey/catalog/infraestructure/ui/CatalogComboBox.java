package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.catalog.application.ShowAllCatalogsUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;

public class CatalogComboBox extends JPanel {
    private JComboBox<Catalog> catalogComboBox;
    private CatalogService catalogService;
    private ShowAllCatalogsUseCase showAllCatalogsUseCase;

    public CatalogComboBox() {
        this.showAllCatalogsUseCase = new ShowAllCatalogsUseCase(catalogService);
        catalogComboBox = new JComboBox<>();

        setLayout(new BorderLayout());
        
        add(catalogComboBox, BorderLayout.CENTER);
    }

    public CatalogComboBox(ActionListener actionListenerComboBox) {
        catalogComboBox = new JComboBox<>();
        catalogComboBox.addActionListener(catalogComboBox);

        setLayout(new BorderLayout());

        add(catalogComboBox, BorderLayout.CENTER);
    }

    public void updateCatalogs() {
        Optional<List<Catalog>> catalogs = showAllCatalogsUseCase.execute(10, 0);
        catalogComboBox.removeAllItems();
        catalogs.get().forEach(catalog -> {
            catalogComboBox.addItem(catalog);
        });
        
        if (catalogComboBox.getItemCount() == 0) {
            catalogComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        catalogComboBox.setEditable(swich);
    }

    public void setSelectedCatalog(Catalog catalog) {
        catalogComboBox.addItem(catalog);
        catalogComboBox.setSelectedItem(catalog);
    }

    public Catalog getSelectedCatalog() {
        return (Catalog) catalogComboBox.getSelectedItem();
    }
}
