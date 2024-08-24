package com.survey.responseOption.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.responseOption.application.ShowAllResponseOptionsUseCase;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;
import com.survey.responseOption.infraestructure.repository.ResponseOptionRepository;

public class ResponseComboBox extends JPanel{
    private  JComboBox<ResponseOption> responseOptionComboBox;
    private boolean isActive = false;

    private ResponseOptionService responseOptionService = new ResponseOptionRepository();
    private ShowAllResponseOptionsUseCase showAllResponseOptionsUseCase;

    public ResponseComboBox() {
        responseOptionComboBox = new JComboBox<>();
        responseOptionComboBox.setPreferredSize(new Dimension(120, 30));
        responseOptionComboBox.setEnabled(false);
        JButton activar = new JButton("x");
        activar.addActionListener(e -> {
            responseOptionComboBox.setEnabled(!isActive);
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
        showAllResponseOptionsUseCase = new ShowAllResponseOptionsUseCase(responseOptionService);
        List<ResponseOption> responses = showAllResponseOptionsUseCase.execute().get();
        responseOptionComboBox.removeAllItems();
        responses.forEach(response -> {
            responseOptionComboBox.addItem(response);
        });

        if (responseOptionComboBox.getItemCount() == 0) {
            responseOptionComboBox.setEnabled(false);
        }
    }

    public void switcher(boolean swich) {
        responseOptionComboBox.setEnabled(swich);
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

    public void setIsActive(boolean swich) {
        isActive = swich;
    }
}
