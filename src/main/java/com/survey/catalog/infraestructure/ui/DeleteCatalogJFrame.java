package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.ui.StyleDefiner;

public class DeleteCatalogJFrame extends JFrame{
    private CatalogComboBox catalogComboBox;
    private JButton returnButton;

    //initializer

    public DeleteCatalogJFrame() {
        initComponents();

        createDeleteCatalog();
    }

    private void initComponents() {
        catalogComboBox = new CatalogComboBox();
    }

    private void createDeleteCatalog() {
        setTitle("Delete Catalog");
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
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabel = new JLabel("Catalog");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        catalogComboBox.updateCatalogs();
        formPanel.add(catalogComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            Catalog catalog = catalogComboBox.getSelectedCatalog();

            int continuar = JOptionPane.showConfirmDialog(catalogComboBox, "seguro que quieres eliminar a este usuario?", "¿?", JOptionPane.YES_NO_OPTION);

            if (continuar == 0) {
                // initializer del delete
                JOptionPane.showMessageDialog(null, "eliminado correctamente", "eliminado", JOptionPane.INFORMATION_MESSAGE);
                catalogComboBox.updateCatalogs();
            }
        });
    }
    
    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
