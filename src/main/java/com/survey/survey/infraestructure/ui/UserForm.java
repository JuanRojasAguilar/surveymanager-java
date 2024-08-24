package com.survey.survey.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.ui.UserFormChapter;
import com.survey.ui.StyleDefiner;

public class UserForm extends JFrame {
    private SurveyComboBox surveyComboBox;
    private JButton returnButton;
    private int row = 0;
    private JButton sendButton = StyleDefiner.defineButtonStyle(new JButton("send"));; 

    private ChapterService chapterService;
    private ShowAllChaptersUseCase showAllChaptersUseCase;

    public UserForm() {
        initComponents();

        createUserForm();
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox();
    }

    private void createUserForm() {
        setTitle("User Form");
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

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST; 
        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        gbc.gridx = 1;
        List<Chapter> surveyChapters = new ArrayList<>();
        JButton confirmButton = new JButton("confirm");
        confirmButton.addActionListener(e -> {
            showAllChaptersUseCase = new ShowAllChaptersUseCase(chapterService);
            List<Chapter> chapters = showAllChaptersUseCase.execute(15, 0).get();
            chapters.forEach(chapter -> {
                if (chapter.getId() == surveyComboBox.getSelectedSurvey().getId()) {
                    surveyChapters.add(chapter);
                }

            });

            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            surveyChapters.forEach(chapter -> {
                row++;
                gbc.gridy = row;
                add(new UserFormChapter(sendButton, chapter), gbc);
            });
            
            row++;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.NONE;
            add(sendButton, gbc);
        });
        formPanel.add(confirmButton, gbc);
        
        JScrollPane jScrollPane = new JScrollPane(formPanel,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(jScrollPane, BorderLayout.CENTER);
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}