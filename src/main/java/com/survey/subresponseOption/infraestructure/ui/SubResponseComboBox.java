package com.survey.subresponseOption.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.subresponseOption.application.ShowAllSubresponseOptionsUseCase;
import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;
import com.survey.subresponseOption.infraestructure.repository.SubresponseOptionRepository;

public class SubResponseComboBox extends JPanel{
    private SubresponseOptionService subresponseOptionService = new SubresponseOptionRepository();
    private ShowAllSubresponseOptionsUseCase showAllSubresponseOptionsUseCase;

    private JComboBox<SubresponseOption> subresponseOptionComboBox;
    
    public SubResponseComboBox() {
        subresponseOptionComboBox = new JComboBox<>();
        subresponseOptionComboBox.setPreferredSize(new Dimension(120, 30));
        
        setLayout(new BorderLayout());

        add(subresponseOptionComboBox, BorderLayout.CENTER);
    }

    public SubResponseComboBox(ActionListener actionListener) {
        subresponseOptionComboBox = new JComboBox<>();
        subresponseOptionComboBox.addActionListener(actionListener);

        setLayout(new BorderLayout());

        add(subresponseOptionComboBox, BorderLayout.CENTER);
    }

    public void updateSubResponses() {
        showAllSubresponseOptionsUseCase = new ShowAllSubresponseOptionsUseCase(subresponseOptionService);
        List<SubresponseOption> subResponses = showAllSubresponseOptionsUseCase.execute().get();
        subresponseOptionComboBox.removeAllItems();
        subResponses.forEach(subResponse -> {
            subresponseOptionComboBox.addItem(subResponse);
        });

        if (subresponseOptionComboBox.getItemCount() == 0) {
            subresponseOptionComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        subresponseOptionComboBox.setEditable(swich);
    }

    public void setSelectedSubResponse(SubresponseOption subResponseOption) {
        subresponseOptionComboBox.addItem(subResponseOption);
        subresponseOptionComboBox.setSelectedItem(subResponseOption);
    }

    public SubresponseOption getSelectedSubResponse() {
        return (SubresponseOption) subresponseOptionComboBox.getSelectedItem();
    }


}
