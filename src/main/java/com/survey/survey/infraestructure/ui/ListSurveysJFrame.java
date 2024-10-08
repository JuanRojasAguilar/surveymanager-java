package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.survey.survey.application.ShowAllSurveysUseCase;
import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;
import com.survey.survey.infraestructure.repository.SurveyRepository;

public class ListSurveysJFrame extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private SurveyComboBox surveyComboBox;
    private JButton returnButton;

    private SurveyService surveyService = new SurveyRepository();
    private ShowAllSurveysUseCase showAllSurveysUseCase;

    private boolean initializer;

    public ListSurveysJFrame() {
        initializer = true;

        initComponents();

        createListSurveys();

        initializer = false;
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox(getSelectedSurvey());
    }

    private void createListSurveys() {
        setTitle("List Survey");
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
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"id", "nombre", "descripcion", "createdAt", "updatedAt"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int columnWidth = 500 / columnNames.length; 
        for (int i = 0; i < columnNames.length; i++ ) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 300)); 
        formPanel.add(scrollPane, gbc);

        showAllSurveys();

        add(formPanel, BorderLayout.CENTER);
    }

    private void showAllSurveys() {
        showAllSurveysUseCase = new ShowAllSurveysUseCase(surveyService);
        List<Survey> surveys = showAllSurveysUseCase.execute().get();

        surveys.forEach(survey -> {
            Object[] rowData = {survey.getId(), survey.getName(), survey.getDescription(), survey.getCreatedAt(), survey.getUpdatedAt()};
            model.addRow(rowData);
        });
    }

    private ActionListener getSelectedSurvey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    Survey survey = surveyComboBox.getSelectedSurvey();
                    model.setRowCount(0);
                    Object[] rowData = {survey.getId(), survey.getName(), survey.getDescription(), survey.getCreatedAt(), survey.getUpdatedAt()};
                    model.addRow(rowData);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
