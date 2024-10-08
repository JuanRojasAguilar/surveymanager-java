package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.survey.catalog.application.ShowAllCatalogsUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;
import com.survey.catalog.infraestructure.repository.CatalogRepository;

public class ListCatalogsJFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private CatalogComboBox catalogComboBox;
    private JButton returnButton;
    private CatalogService catalogService = new CatalogRepository();

    private boolean initializer;

    public ListCatalogsJFrame() {
        initializer = true;

        initComponents();

        createListCatalogs();

        initializer = false;
    }

    private void initComponents() {
        catalogComboBox = new CatalogComboBox(getSelectedCatalog());
    }

    private void createListCatalogs() {
        setTitle("List Catalogs");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnButton = new JButton("<--");
        topPanel.add(returnButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        int row = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabel = new JLabel("Catalog");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        catalogComboBox.updateCatalogs();
        formPanel.add(catalogComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"id", "nombre", "createdAt", "updatedAt"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int columnWidth = 500 / columnNames.length; 
        for (int i = 0; i < columnNames.length; i++ ) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300)); 
        formPanel.add(scrollPane, gbc);

        showAllCatalogs();

        add(formPanel, BorderLayout.CENTER);
    }

    private void showAllCatalogs() {
        List<Catalog> catalogs = new ShowAllCatalogsUseCase(catalogService).execute(10, 0).get();
        catalogs.forEach(catalog -> {
            Object[] rowData = {catalog.getId(), catalog.getName(), catalog.getCreatedAt(), catalog.getUpdatedAt()};
            model.addRow(rowData);
        });
    }

    private ActionListener getSelectedCatalog() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    Catalog catalog = catalogComboBox.getSelectedCatalog();
                    model.setRowCount(0);
                    Object[] rowData = {catalog.getId(), catalog.getName(), catalog.getCreatedAt(), catalog.getUpdatedAt()};
                    model.addRow(rowData);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
