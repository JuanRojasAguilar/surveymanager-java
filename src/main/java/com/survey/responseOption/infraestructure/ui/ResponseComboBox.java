package com.survey.responseOption.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.question.domain.entity.Question;
import com.survey.responseOption.domain.entity.ResponseOption;

public class ResponseComboBox extends JPanel{
    private  JComboBox<ResponseOption> responseOptionComboBox;
    private boolean isActive = false;

    public ResponseComboBox() {
        responseOptionComboBox = new JComboBox<>();
        JButton activar = new JButton("x");
        activar.addActionListener(e -> {
            responseOptionComboBox.setEditable(!isActive);
            isActive = !isActive;
        });

        setLayout(new BorderLayout());

        add(responseOptionComboBox, BorderLayout.CENTER);
        add(activar, BorderLayout.EAST);
    }

    public ResponseComboBox(ActionListener actionListenerComboBox) {
        responseOptionComboBox = new JComboBox<>();
        responseOptionComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(responseOptionComboBox, BorderLayout.CENTER);
    }

    public void updateResponses() {
        List<ResponseOption> responses = //initializerResponses
        responseOptionComboBox.removeAllItems();
        responses.forEach(response -> {
            responseOptionComboBox.addItem(response);
        });

        if (responseOptionComboBox.getItemCount() == 0) {
            responseOptionComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        responseOptionComboBox.setEditable(swich);
    }

    public void setSelectedResponse(ResponseOption responseOption) {
        responseOptionComboBox.addItem(responseOption);
        responseOptionComboBox.setSelectedItem(responseOption);
    }

    public ResponseOption getSelectedResponse() {
        return (ResponseOption) responseOptionComboBox.getSelectedItem();
    }

    public boolean isActive() {
        return isActive;
    }
}
