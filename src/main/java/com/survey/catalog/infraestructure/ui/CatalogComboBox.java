package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.survey.catalog.domain.entity.Catalog;

public class CatalogComboBox extends JPanel {
    private JComboBox<Catalog> catalogComboBox;

    public CatalogComboBox() {
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
        List<Catalog> catalogs = //initializercatalog
        catalogComboBox.removeAllItems();
        catalogs.forEach(catalog -> {
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
