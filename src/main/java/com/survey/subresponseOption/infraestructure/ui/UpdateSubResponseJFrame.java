package com.survey.subresponseOption.infraestructure.ui;

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
import com.survey.question.domain.entity.Question;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.infraestructure.ui.ResponseComboBox;
import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.ui.StyleDefiner;

public class UpdateSubResponseJFrame extends JFrame{
    //initializer de SubResponse
    private JButton returnButton;

    private SubResponseComboBox subResponseComboBox;
    private ResponseComboBox responseComboBox;
    private JTextField subResponseTextField;
    private JButton updateButton;

    private boolean initializer;
    private SubresponseOption subResponseToEdit;

    public UpdateSubResponseJFrame() {
        initializer = true;

        initComponents();
        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        responseComboBox = new ResponseComboBox();
        subResponseComboBox = new SubResponseComboBox(getSelectedSubResponse());
    }

    private void createUpdateFrame() {
        setTitle("Update SubResponse");
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
        JLabel comboBoxLabel = new JLabel("subResponse");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        subResponseComboBox.updateSubResponses();
        formPanel.add(subResponseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabelResponse = new JLabel("Response: ");
        formPanel.add(comboBoxLabelResponse, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        responseComboBox.switcher(false);
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel subResponseLabel = new JLabel("Subresponse: ");
        formPanel.add(subResponseLabel, gbc);

        gbc.gridx = 1;
        subResponseTextField = StyleDefiner.defineFieldStyle(subResponseTextField);
        subResponseTextField.setEditable(false);
        formPanel.add(subResponseTextField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("actualizar"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarSubResponse());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarSubResponse() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String subResponseText = subResponseTextField.getText();
                ResponseOption response = responseComboBox.getSelectedResponse();

                if (subResponseText.isEmpty()) {
                    JOptionPane.showMessageDialog(subResponseTextField, "campos incompletos", "error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                subResponseTextField.setText("");

                SubresponseOption subresponseOption = new SubresponseOption();
                subresponseOption.setSubresponseText(subResponseText);
                subresponseOption.setIdResponseOptions(response.getId());

                //initializer

                JOptionPane.showMessageDialog(subResponseTextField, "response actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }  
        };
    }

    private ActionListener getSelectedSubResponse() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    subResponseToEdit = subResponseComboBox.getSelectedSubResponse();

                    subResponseTextField.setText(subResponseToEdit.getSubresponseText());
                    subResponseTextField.setEditable(true);

                    // initializer de response

                    responseComboBox.setSelectedResponse(response);
                    responseComboBox.switcher(true);
                    
                    updateButton.setEnabled(true);

                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
