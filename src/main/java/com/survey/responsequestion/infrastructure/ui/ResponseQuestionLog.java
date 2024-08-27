package com.survey.responsequestion.infrastructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.survey.responsequestion.application.GetAllResponseQuestionDatesUseCase;
import com.survey.responsequestion.application.GetAllResponseQuestionUseCase;
import com.survey.responsequestion.domain.entity.ResponseQuestionDto;
import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.responsequestion.infrastructure.repository.ResponseQuestionRepository;
import com.survey.survey.infraestructure.ui.SurveyComboBox;
import com.survey.ui.StyleDefiner;

public class ResponseQuestionLog extends JFrame{
    private SurveyComboBox surveyComboBox;
    private JComboBox<Timestamp> dateComboBox;
    private JButton returnButton;
    private JButton showButton;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;

    private ResponseQuestionService responseQuestionService = new ResponseQuestionRepository();
    private GetAllResponseQuestionDatesUseCase getAllResponseQuestionDatesUseCase;
    private GetAllResponseQuestionUseCase getAllResponseQuestionUseCase;

    private boolean initializer;

    public ResponseQuestionLog() {
        initializer = true;

        initComponents();

        createQuestionLog();

        initializer = false;
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox(updateDates());
        dateComboBox = new JComboBox<>();
        
        showButton = StyleDefiner.defineButtonStyle(new JButton("VER"));
        showButton.setEnabled(false);
    }

    private void createQuestionLog() {
        setTitle("Survey Log");
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
        gbc.anchor = GridBagConstraints.WEST;
        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        gbc.gridx = 1;
        formPanel.add(dateComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        showButton.addActionListener(showSurveyResponses());
        formPanel.add(showButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"chapter", "question", "response", "subresponse"};
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


        add(formPanel, BorderLayout.CENTER);
    }

    
    private ActionListener updateDates() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    getAllResponseQuestionDatesUseCase = new GetAllResponseQuestionDatesUseCase(responseQuestionService);
                    List<Timestamp> dates = getAllResponseQuestionDatesUseCase.execute(surveyComboBox.getSelectedSurvey());
    
                    dateComboBox.removeAllItems();
                    dates.forEach(date -> {
                        dateComboBox.addItem(date);
                    });

                    showButton.setEnabled(true);
                    revalidate();
                    repaint();
                }
            }
            
        };
    }

    private ActionListener showSurveyResponses() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                getAllResponseQuestionUseCase = new GetAllResponseQuestionUseCase(responseQuestionService);
                Timestamp parseDate = (Timestamp) dateComboBox.getSelectedItem();
                List<ResponseQuestionDto> responses = getAllResponseQuestionUseCase.execute(parseDate, surveyComboBox.getSelectedSurvey());
                model.setRowCount(0);
                responses.forEach(response -> {
                    Object[] rowData = {response.getChapter(), response.getQuestion(), response.getResponse(), response.getSubResponse()};
                    model.addRow(rowData);
                });
            }
            
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}