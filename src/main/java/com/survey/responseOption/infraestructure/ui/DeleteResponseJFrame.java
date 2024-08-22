package com.survey.responseOption.infraestructure.ui;

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

import com.survey.question.domain.entity.Question;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.ui.StyleDefiner;

public class DeleteResponseJFrame extends JFrame{
    private ResponseComboBox responseComboBox;        
    private JButton returnButton;

    //initializer

    public DeleteResponseJFrame() {
        initComponents();

        createDeleteResponse();
    }

    private void initComponents() {
        responseComboBox = new ResponseComboBox();
    }

    private void createDeleteResponse() {
        setTitle("Delete Response");
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
        JLabel comboBoxLabel = new JLabel("Response");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        formPanel.add(responseComboBox);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            ResponseOption responseOption = responseComboBox.getSelectedResponse();

            int continuar = JOptionPane.showConfirmDialog(responseComboBox, "seguro que quieres eliminar a este response?", "¿?", JOptionPane.YES_NO_OPTION);

            if (continuar == 0) {
                // initializer del delete
                JOptionPane.showMessageDialog(null, "eliminado correctamente", "eliminado", JOptionPane.INFORMATION_MESSAGE);
                responseComboBox.updateResponses();
            }
        });
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
