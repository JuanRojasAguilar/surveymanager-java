package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class SurveyComboBox extends JPanel{
    //survey Initializer
    private JComboBox<Survey> surveyComboBox;

    public SurveyComboBox() {
        surveyComboBox = new SurveyComboBox();

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public SurveyComboBox(ActionListener actionListenerComboBox) {
        surveyComboBox = new SurveyComboBox();
        surveyComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public void updateSurveys() {
        List<Survey> surveys = //initializerSurvey
        surveyComboBox.removeAllItems();
        surveys.forEach(survey -> {
            surveyComboBox.addItem(survey);
        });

        if (surveyComboBox.getItemCount() == 0) {
            surveyComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        surveyComboBox.setEditable(swich);
    }

    public void setSelectedSurvey(Survey survey) {
        surveyComboBox.addItem(survey);
        surveyCombobox.setSelectedItem(survey);
    }

    public Survey getSelectedSurvey() {
        return surveyComboBox.getSelectedItem();
    }
}
