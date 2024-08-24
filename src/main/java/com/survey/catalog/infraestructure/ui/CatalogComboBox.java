package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.survey.catalog.application.ShowAllCatalogsUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;
import com.survey.catalog.infraestructure.repository.CatalogRepository;

public class CatalogComboBox extends JPanel {
    private JComboBox<Catalog> catalogComboBox;
    private JButton activar;
    private boolean isActive = false;

    private CatalogService catalogService = new CatalogRepository();
    private ShowAllCatalogsUseCase showAllCatalogsUseCase;

    public CatalogComboBox() {
        catalogComboBox = new JComboBox<>();
        catalogComboBox.setPreferredSize(new Dimension(120, 30));
        catalogComboBox.setEnabled(false);
        activar = new JButton("x");
        activar.addActionListener(e -> {
            catalogComboBox.setEnabled(!isActive);
            isActive = !isActive;
        });

        setLayout(new BorderLayout());
        
        add(catalogComboBox, BorderLayout.CENTER);
        add(activar, BorderLayout.EAST);
    }

    public CatalogComboBox(ActionListener actionListenerComboBox) {
        catalogComboBox = new JComboBox<>();
        catalogComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(catalogComboBox, BorderLayout.CENTER);
    }

    public void updateCatalogs() {
        showAllCatalogsUseCase = new ShowAllCatalogsUseCase(catalogService);
        List<Catalog> catalogs = showAllCatalogsUseCase.execute(10, 0).get();
        catalogComboBox.removeAllItems();
        catalogs.forEach(catalog -> {
            catalogComboBox.addItem(catalog);
        });
        
        if (catalogComboBox.getItemCount() == 0) {
            catalogComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        catalogComboBox.setEnabled(swich);
        activar.setEnabled(swich);
        revalidate();
        repaint();
    }

    public void setSelectedCatalog(Catalog catalog) {
        catalogComboBox.addItem(catalog);
        catalogComboBox.setSelectedItem(catalog);
    }

    public Catalog getSelectedCatalog() {
        return (Catalog) catalogComboBox.getSelectedItem();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean swich) {
        isActive = swich;
    }
}
