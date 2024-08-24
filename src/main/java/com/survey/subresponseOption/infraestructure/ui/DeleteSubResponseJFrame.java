package com.survey.subresponseOption.infraestructure.ui;

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

import com.survey.subresponseOption.application.DeleteSubresponseOptionUseCase;
import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;
import com.survey.subresponseOption.infraestructure.repository.SubresponseOptionRepository;
import com.survey.ui.StyleDefiner;

public class DeleteSubResponseJFrame extends JFrame{
    private SubResponseComboBox subResponseComboBox;
    private JButton returnButton;

    private SubresponseOptionService subresponseOptionService = new SubresponseOptionRepository();
    private DeleteSubresponseOptionUseCase deleteSubresponseOptionUseCase;

    public DeleteSubResponseJFrame() {
        initComponents();

        createDeleteSubResponse();
    }

    private void initComponents() {
        subResponseComboBox = new SubResponseComboBox();
    }

    private void createDeleteSubResponse() {
        setTitle("Delete SubResponse");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

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
        JLabel comboBoxLabel = new JLabel("SubResponse");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        subResponseComboBox.updateSubResponses();
        formPanel.add(subResponseComboBox);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            SubresponseOption subResponseOption = subResponseComboBox.getSelectedSubResponse();
            
            int continuar = JOptionPane.showConfirmDialog(subResponseComboBox, "seguro que quieres eliminar a este response?", "¿?", JOptionPane.YES_NO_OPTION);
            
            if (continuar == 0) {
                deleteSubresponseOptionUseCase = new DeleteSubresponseOptionUseCase(subresponseOptionService);
                boolean hasBeenDeleted = deleteSubresponseOptionUseCase.execute(subResponseOption.getId()); 
                String mensaje = hasBeenDeleted ? "eliminado correctamente" : "No hemos podido eliminarlo, intenta de nuevo";
                JOptionPane.showMessageDialog(null, mensaje, "eliminado", JOptionPane.INFORMATION_MESSAGE);
                subResponseComboBox.updateSubResponses();
            }
        });
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
