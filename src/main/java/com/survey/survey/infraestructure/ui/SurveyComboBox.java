package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.survey.application.ShowAllSurveysUseCase;
import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;
import com.survey.survey.infraestructure.repository.SurveyRepository;

public class SurveyComboBox extends JPanel{
    private JComboBox<Survey> surveyComboBox;

    private ShowAllSurveysUseCase showAllSurveysUseCase;
    private SurveyService surveyService = new SurveyRepository();

    public SurveyComboBox() {
        surveyComboBox = new JComboBox<>();
        surveyComboBox.setPreferredSize(new Dimension(120, 30));

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public SurveyComboBox(ActionListener actionListenerComboBox) {
        surveyComboBox = new JComboBox<>();
        surveyComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(surveyComboBox, BorderLayout.CENTER);
    }

    public void updateSurveys() {
        showAllSurveysUseCase = new ShowAllSurveysUseCase(surveyService);
        List<Survey> surveys = showAllSurveysUseCase.execute().get();
        
        surveyComboBox.removeAllItems();
        surveys.forEach(survey -> {
            surveyComboBox.addItem(survey);
        });

        if (surveyComboBox.getItemCount() == 0) {
            surveyComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        surveyComboBox.setEnabled(swich);
        revalidate();
        repaint();
    }

    public void setSelectedSurvey(Survey survey) {
        surveyComboBox.addItem(survey);
        surveyComboBox.setSelectedItem(survey);
    }

    public Survey getSelectedSurvey() {
        return (Survey) surveyComboBox.getSelectedItem();
    } 
}
