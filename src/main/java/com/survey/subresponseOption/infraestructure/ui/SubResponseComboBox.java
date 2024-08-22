package com.survey.subresponseOption.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.subresponseOption.domain.entity.SubresponseOption;

public class SubResponseComboBox extends JFrame{
    private JComboBox<SubresponseOption> subresponseOptionComboBox;
    
    public SubResponseComboBox() {
        subresponseOptionComboBox = new JComboBox<>();
        
        setLayout(new BorderLayout());

        add(subresponseOptionComboBox, BorderLayout.CENTER);
    }

    public SubResponseComboBox(ActionListener actionListener) {
        subresponseOptionComboBox = new JComboBox<>();

        setLayout(new BorderLayout());

        add(subresponseOptionComboBox, BorderLayout.CENTER);
    }

    public void updateSubResponses() {
        List<SubresponseOption> subResponses = //initializerResponses
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
