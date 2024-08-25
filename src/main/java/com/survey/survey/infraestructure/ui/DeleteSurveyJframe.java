package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.survey.survey.application.DeleteSurveyUseCase;
import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;
import com.survey.survey.infraestructure.repository.SurveyRepository;
import com.survey.ui.StyleDefiner;

public class DeleteSurveyJframe extends JFrame {
    private SurveyComboBox surveyComboBox;
    private JButton returnButton;

    private SurveyService surveyService = new SurveyRepository();
    private DeleteSurveyUseCase deleteSurveyUseCase;

    public DeleteSurveyJframe() {
        initComponents();

        createDeleteSurvey();
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox();
    }

    private void createDeleteSurvey() {
        setTitle("Delete Survey");
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabel = new JLabel("survey");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            Survey survey = surveyComboBox.getSelectedSurvey();

            int continuar = JOptionPane.showConfirmDialog(surveyComboBox, "seguro que quieres eliminar a este usuario?", "¿?", JOptionPane.YES_NO_OPTION);

            if (continuar == 0) {
                deleteSurveyUseCase = new DeleteSurveyUseCase(surveyService);
                boolean hasBeenDeleted = deleteSurveyUseCase.execute(survey.getId());
                String mensaje = hasBeenDeleted ? "eliminado correctamente" : "No hemos podido eliminarlo, intenta de nuevo";
                JOptionPane.showMessageDialog(null, mensaje, "eliminado", JOptionPane.INFORMATION_MESSAGE);
                surveyComboBox.updateSurveys();
            }
        });
        formPanel.add(deleteButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
