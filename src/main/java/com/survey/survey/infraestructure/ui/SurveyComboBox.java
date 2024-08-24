package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;

public class SurveyComboBox extends JPanel{
    //survey Initializer
    private JComboBox<Survey> surveyComboBox;
    private ShowAllSurveysUseCase showAllSurveysUseCase;
    private SurveyService surveyService;

    public SurveyComboBox() {
        surveyComboBox = new JComboBox();

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public SurveyComboBox(ActionListener actionListenerComboBox) {
        surveyComboBox = new JComboBox();
        surveyComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public void updateSurveys() {
        showAllSurveysUseCase = new ShowAllSurveysUseCase(surveyService);
        List<Survey> surveys = showAllSurveysUseCase.execute(10, 0).get();
        
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
        surveyComboBox.setSelectedItem(survey);
    }

    public Survey getSelectedSurvey() {
        return (Survey) surveyComboBox.getSelectedItem();
    } 
}
