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

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.infraestructure.ui.ResponseComboBox;
import com.survey.subresponseOption.application.AddSubresponseOptionUseCase;
import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;
import com.survey.subresponseOption.infraestructure.repository.SubresponseOptionRepository;
import com.survey.ui.StyleDefiner;

public class CreateSubResponseJFrame extends JFrame {
    private SubresponseOptionService subresponseOptionService = new SubresponseOptionRepository();
    private AddSubresponseOptionUseCase addSubresponseOptionUseCase;

    private JButton returnButton;

    private ResponseComboBox responseComboBox;
    private JTextField subResponseTextfield;

    public CreateSubResponseJFrame() {
        initComponents();
        createCreateFrame();
    }

    private void initComponents() {
        responseComboBox = new ResponseComboBox();
    }

    private void createCreateFrame() {
        setTitle("Create SubResponse");
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
        JLabel comboBoxLabelQuestion = new JLabel("SubResponse: ");
        formPanel.add(comboBoxLabelQuestion, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel subResponsetLabel = new JLabel("subResponset: ");
        formPanel.add(subResponsetLabel, gbc);

        gbc.gridx = 1;
        subResponseTextfield = StyleDefiner.defineFieldStyle(subResponseTextfield);
        formPanel.add(subResponseTextfield, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        createButton.addActionListener(guardarSubResponse());
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener guardarSubResponse() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addSubresponseOptionUseCase = new AddSubresponseOptionUseCase(subresponseOptionService);

                String subResponseText = subResponseTextfield.getText();
                ResponseOption response = responseComboBox.getSelectedResponse();

                if (subResponseText.isEmpty()) {
                    JOptionPane.showMessageDialog(subResponseTextfield, "campos incompletos", "error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                subResponseTextfield.setText("");

                SubresponseOption subresponseOption = new SubresponseOption();
                subresponseOption.setSubresponseText(subResponseText);
                subresponseOption.setIdResponseOption(response.getId());

                addSubresponseOptionUseCase.execute(subresponseOption);

                JOptionPane.showMessageDialog(subResponseTextfield, "response guardado", "accion completada", JOptionPane.WARNING_MESSAGE);
            } 
        };   
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}


