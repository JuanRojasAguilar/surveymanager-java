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

import com.survey.catalog.application.AddCatalogUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;
import com.survey.catalog.infraestructure.repository.CatalogRepository;
import com.survey.ui.StyleDefiner;

public class CreateCatalogJFrame extends JFrame{
    private CatalogService catalogService = new CatalogRepository();
    private AddCatalogUseCase addCatalogUseCase;

    private JButton returnButton;

    private JTextField nombreField;

    public CreateCatalogJFrame() {
        createCreateFrame();
    }

    private void createCreateFrame() {
        setTitle("Create Survey");
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
        JLabel nombreLabel = new JLabel("nombre: ");
        formPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(nombreField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        createButton.addActionListener(guardarCatalog());
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener guardarCatalog() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addCatalogUseCase = new AddCatalogUseCase(catalogService);
                String nombre = nombreField.getText();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(nombreField, "campos incompletos", "erroe", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                nombreField.setText("");

                Catalog catalog = new Catalog(nombre);

                addCatalogUseCase.execute(catalog);

                JOptionPane.showMessageDialog(nombreField, "usuario guardado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
            
        };   
    }
    
    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
