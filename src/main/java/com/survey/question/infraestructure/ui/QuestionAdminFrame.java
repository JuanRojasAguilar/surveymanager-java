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
import javax.swing.JPanel;

import com.survey.catalog.infraestructure.ui.CreateCatalogJFrame;
import com.survey.catalog.infraestructure.ui.DeleteCatalogJFrame;
import com.survey.catalog.infraestructure.ui.ListCatalogsJFrame;
import com.survey.catalog.infraestructure.ui.UpdateCatalogJFrame;

public class QuestionAdminFrame extends JFrame {
    private JButton returnButton;

    public QuestionAdminFrame() {
        createQuestionFrame();
    }

    private void createQuestionFrame() {
        setTitle("QuestionFrame");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        returnButton = new JButton("<--");
        topPanel.add(returnButton);
        add(topPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        int row = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = new JButton("createManager");
        createButton.setPreferredSize(new Dimension(175, 75));
        createButton.addActionListener(e -> {
            CreateQuestionJFrame createQuestionJFrame = new CreateQuestionJFrame();
            createQuestionJFrame.setReturnActionListener(returnSetVisibleFunction(createQuestionJFrame));
            createQuestionJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            createQuestionJFrame.setVisible(true);
        });
        buttonsPanel.add(createButton, gbc);

        gbc.gridx = 1;
        JButton deleteButton = new JButton("deleteManager");
        deleteButton.setPreferredSize(new Dimension(175, 75));
        deleteButton.addActionListener(e -> {
            DeleteQuestionJFrame deleteQuestionJframe = new DeleteQuestionJFrame();
            deleteQuestionJframe.setReturnActionListener(returnSetVisibleFunction(deleteQuestionJframe));
            deleteQuestionJframe.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            deleteQuestionJframe.setVisible(true);
        });
        buttonsPanel.add(deleteButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        JButton updateButton = new JButton("updateManager");
        updateButton.setPreferredSize(new Dimension(175, 75));
        updateButton.addActionListener(e -> {
            UpdateQuestionJFrame updateQuestionJFrame = new UpdateQuestionJFrame();
            updateQuestionJFrame.setReturnActionListener(returnSetVisibleFunction(updateQuestionJFrame));
            updateQuestionJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            updateQuestionJFrame.setVisible(true);
            
        });
        buttonsPanel.add(updateButton, gbc);

        gbc.gridx = 1;
        JButton listButton = new JButton("listManager");
        listButton.setPreferredSize(new Dimension(175, 75));
        listButton.addActionListener(e -> {
            ListQuestionsJFrame listQuestionsJFrame = new ListQuestionsJFrame();
            listQuestionsJFrame.setReturnActionListener(returnSetVisibleFunction(listQuestionsJFrame));
            listQuestionsJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            listQuestionsJFrame.setVisible(true);
        });
        buttonsPanel.add(listButton, gbc);

        add(buttonsPanel, BorderLayout.CENTER);
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

    private ActionListener returnSetVisibleFunction(JFrame panelToDispose) {
        return new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    panelToDispose.dispose();
                    setVisible(true);
                }
        };
    }
}
