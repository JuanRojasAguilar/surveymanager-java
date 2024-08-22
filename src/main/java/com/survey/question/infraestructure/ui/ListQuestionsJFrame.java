package com.survey.question.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.survey.chapter.infraestructure.ui.ChapterComboBox;
import com.survey.question.domain.entity.Question;

public class ListQuestionsJFrame extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private QuestionComboBox questionComboBox;
    private JButton returnButton;

    private boolean initializer;

    //initializer

    public ListQuestionsJFrame() {
        initializer = true;

        initComponents();

        createListQuestions();

        initializer = false;
    }

    private void initComponents() {
        questionComboBox = new QuestionComboBox(getSelectedQuestion());
    }

    private void createListQuestions() {
        setTitle("List Questions");
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
        JLabel comboBoxLabel = new JLabel("Question");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        questionComboBox.updateQuestions();
        formPanel.add(questionComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"id", "idChapter", "questionNumber", "responseType", "questionText", "createdAt", "updateAt"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        int columnWidth = 500 / columnNames.length; 
        for (int i = 0; i < columnNames.length; i++ ) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);
        }

        scrollPane.setPreferredSize(new Dimension(500, 300)); 
        formPanel.add(scrollPane, gbc);

        showAllQuestions();

        add(formPanel, BorderLayout.CENTER);

    }

    private void showAllQuestions() {
        //list from initializer

        questions.forEach(question -> {
            Object[] rowData = {question.getId(), question.getIdChapter(), question.getQuestionNumber(), question.getResponseType(), question.getQuestionText(), question.getCreateAt(), question.getUpdateAt()};
            model.addRow(rowData);
        });
    }

    private ActionListener getSelectedQuestion() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    Question question = questionComboBox.getSelectedQuestion();
                    model.setRowCount(0);
                    Object[] rowData = {question.getId(), question.getIdChapter(), question.getQuestionNumber(), question.getResponseType(), question.getQuestionText(), question.getCreateAt(), question.getUpdateAt()};
                    model.addRow(rowData);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
