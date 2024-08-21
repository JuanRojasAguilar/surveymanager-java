package com.survey.catalog.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.ui.StyleDefiner;

public class UpdateCatalogJFrame extends JFrame{
        private JButton returnButton;

        private CatalogComboBox catalogComboBox;
        private JTextField nombreField;
        private JButton updateButton;

        private boolean initializer;

        private int idToUpdate;
    

    public UpdateCatalogJFrame() {
        
        initializer = true;

        initComponents();

        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        catalogComboBox = new CatalogComboBox(getSelectedCatalog());
    }

    private void createUpdateFrame() {
        setTitle("Update Catalog");
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
        JLabel comboBoxLabel = new JLabel("catalog");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        catalogComboBox.updateCatalogs();
        formPanel.add(catalogComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel nombreLabel = new JLabel("nombre: ");
        formPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = StyleDefiner.defineFieldStyle(new JTextField(20));
        nombreField.setEditable(false);
        formPanel.add(nombreField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("actualizar"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarCatalog());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarCatalog() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String nombre = nombreField.getText();
                
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(nombreField, "campos incompletos", "erroe", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Catalog catalog = new Catalog(nombre);
                catalog.setId(idToUpdate);

                //initializer

                JOptionPane.showMessageDialog(nombreField, "usuario actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    private ActionListener getSelectedCatalog() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    Catalog catalog = catalogComboBox.getSelectedCatalog();
                    nombreField.setText(catalog.getName());
                    nombreField.setEditable(true);
                    updateButton.setEnabled(true);


                    idToUpdate = catalog.getId();
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

}