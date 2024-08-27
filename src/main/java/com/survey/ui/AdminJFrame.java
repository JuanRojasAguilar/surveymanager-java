package com.survey.ui;

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

import com.survey.catalog.infraestructure.ui.CatalogAdminFrame;
import com.survey.chapter.infraestructure.ui.ChapterAdminFrame;
import com.survey.question.infraestructure.ui.QuestionAdminFrame;
import com.survey.responseOption.infraestructure.ui.ResponseAdminFrame;
import com.survey.responsequestion.infrastructure.ui.ResponseQuestionLog;
import com.survey.subresponseOption.infraestructure.ui.SubResponseAdminFrame;
import com.survey.survey.infraestructure.ui.SurveyAdminFrame;

public class AdminJFrame extends JFrame {

    private JButton returnButton;

    public AdminJFrame() {
        createAdminFrame();

        setVisible(true);
        
    }

    private void createAdminFrame() {
        setTitle("AdminFrame");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
        JButton surveyButton = new JButton("surveyManager");
        surveyButton.setPreferredSize(new Dimension(175, 75));
        surveyButton.addActionListener(e -> {
            SurveyAdminFrame surveyFrame = new SurveyAdminFrame();
            surveyFrame.setReturnActionListener(returnSetVisibleFunction(surveyFrame));
            surveyFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            surveyFrame.setVisible(true);
        });
        buttonsPanel.add(surveyButton, gbc);

        gbc.gridx = 1;
        JButton chapterButton = new JButton("chapterManager");
        chapterButton.setPreferredSize(new Dimension(175, 75));
        chapterButton.addActionListener(e -> {
            ChapterAdminFrame chapterAdminFrame = new ChapterAdminFrame();
            chapterAdminFrame.setReturnActionListener(returnSetVisibleFunction(chapterAdminFrame));
            chapterAdminFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            chapterAdminFrame.setVisible(true);
        });
        buttonsPanel.add(chapterButton, gbc);

        gbc.gridx = 2;
        JButton questionButton = new JButton("questionManager");
        questionButton.setPreferredSize(new Dimension(175, 75));
        questionButton.addActionListener(e -> {
            QuestionAdminFrame questionAdminFrame = new QuestionAdminFrame();
            questionAdminFrame.setReturnActionListener(returnSetVisibleFunction(questionAdminFrame));
            questionAdminFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            questionAdminFrame.setVisible(true);
        });
        buttonsPanel.add(questionButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        JButton responseButton = new JButton("responseManager");
        responseButton.setPreferredSize(new Dimension(175, 75));
        responseButton.addActionListener(e -> {
            ResponseAdminFrame responseAdminFrame = new ResponseAdminFrame();
            responseAdminFrame.setReturnActionListener(returnSetVisibleFunction(responseAdminFrame));
            responseAdminFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            responseAdminFrame.setVisible(true);
            
        });
        buttonsPanel.add(responseButton, gbc);

        gbc.gridx = 1;
        JButton subResponseButton = new JButton("subResponseManager");
        subResponseButton.setPreferredSize(new Dimension(175, 75));
        subResponseButton.addActionListener(e -> {
            SubResponseAdminFrame subResponseAdminFrame = new SubResponseAdminFrame();
            subResponseAdminFrame.setReturnActionListener(returnSetVisibleFunction(subResponseAdminFrame));
            subResponseAdminFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            subResponseAdminFrame.setVisible(true);
        });
        buttonsPanel.add(subResponseButton, gbc);

        gbc.gridx = 2;
        JButton catalogButton = new JButton("catalogManager");
        catalogButton.setPreferredSize(new Dimension(175, 75));
        catalogButton.addActionListener(e -> {
            CatalogAdminFrame catalogAdminFrame = new CatalogAdminFrame();
            catalogAdminFrame.setReturnActionListener(returnSetVisibleFunction(catalogAdminFrame));
            catalogAdminFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            catalogAdminFrame.setVisible(true);
        });
        buttonsPanel.add(catalogButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 1;
        JButton logButton = new JButton("logManager");
        logButton.setPreferredSize(new Dimension(175, 75));
        logButton.addActionListener(e -> {
            ResponseQuestionLog responseQuestionLog = new ResponseQuestionLog();
            responseQuestionLog.setReturnActionListener(returnSetVisibleFunction(responseQuestionLog));
            responseQuestionLog.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            responseQuestionLog.setVisible(true);
            
        });
        buttonsPanel.add(logButton, gbc);

        add(buttonsPanel, BorderLayout.CENTER);
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

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

}
