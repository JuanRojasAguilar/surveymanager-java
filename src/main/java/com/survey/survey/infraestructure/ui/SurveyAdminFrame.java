package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class SurveyAdminFrame extends JFrame {
    private ActionListener setVisibleParent;

    public SurveyAdminFrame(ActionListener setVisibleParent) {
        this.setVisibleParent = setVisibleParent;

        createSurveyFrame();
    }

    private void createSurveyFrame() {
        setTitle("SurveyFrame");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnButton = new JButton("<--");
        returnButton.addActionListener(setVisibleParent);
        topPanel.add(returnButton);
        add(topPanel, BorderLayout.NORTH);

        
    }    
}
