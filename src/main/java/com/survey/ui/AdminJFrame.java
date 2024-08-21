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

import com.survey.survey.infraestructure.ui.SurveyAdminFrame;

public class AdminJFrame extends JFrame {

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
        JButton returnButton = new JButton("<--");
        returnButton.addActionListener(e -> {
            setVisible(false);
            new Login();
        });
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
            setVisible(false);
            // metodo para invocar un chapter manager
        });
        buttonsPanel.add(chapterButton, gbc);

        gbc.gridx = 2;
        JButton questionButton = new JButton("questionManager");
        questionButton.setPreferredSize(new Dimension(175, 75));
        questionButton.addActionListener(e -> {
            setVisible(false);
            // metodo para invocar un question manager
        });
        buttonsPanel.add(questionButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        JButton responseButton = new JButton("responseManager");
        responseButton.setPreferredSize(new Dimension(175, 75));
        responseButton.addActionListener(e -> {
            setVisible(false);
            // metodo para invocar un response manager
        });
        buttonsPanel.add(responseButton, gbc);

        gbc.gridx = 1;
        JButton subResponseButton = new JButton("subResponseManager");
        subResponseButton.setPreferredSize(new Dimension(175, 75));
        subResponseButton.addActionListener(e -> {
            setVisible(false);
            // metodo para invocar un subResponse manager
        });
        buttonsPanel.add(subResponseButton, gbc);

        gbc.gridx = 2;
        JButton catalogButton = new JButton("catalogManager");
        catalogButton.setPreferredSize(new Dimension(175, 75));
        catalogButton.addActionListener(e -> {
            setVisible(false);
            // metodo para invocar un catalog manager
        });
        buttonsPanel.add(catalogButton, gbc);


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

}
