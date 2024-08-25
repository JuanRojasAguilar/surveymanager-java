package com.survey.subresponseOption.infraestructure.ui;

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

public class SubResponseAdminFrame extends JFrame {
    private JButton returnButton;

    public SubResponseAdminFrame() {
        createSubResponseFrame();
    }

    private void createSubResponseFrame() {
        setTitle("SubResponseFrame");
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
            CreateSubResponseJFrame createSubResponseJFrame = new CreateSubResponseJFrame();
            createSubResponseJFrame.setReturnActionListener(returnSetVisibleFunction(createSubResponseJFrame));
            createSubResponseJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            createSubResponseJFrame.setVisible(true);
        });
        buttonsPanel.add(createButton, gbc);

        gbc.gridx = 1;
        JButton deleteButton = new JButton("deleteManager");
        deleteButton.setPreferredSize(new Dimension(175, 75));
        deleteButton.addActionListener(e -> {
            DeleteSubResponseJFrame deleteSubResponseJframe = new DeleteSubResponseJFrame();
            deleteSubResponseJframe.setReturnActionListener(returnSetVisibleFunction(deleteSubResponseJframe));
            deleteSubResponseJframe.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            deleteSubResponseJframe.setVisible(true);
        });
        buttonsPanel.add(deleteButton, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        JButton updateButton = new JButton("updateManager");
        updateButton.setPreferredSize(new Dimension(175, 75));
        updateButton.addActionListener(e -> {
            UpdateSubResponseJFrame updateSubResponseJFrame = new UpdateSubResponseJFrame();
            updateSubResponseJFrame.setReturnActionListener(returnSetVisibleFunction(updateSubResponseJFrame));
            updateSubResponseJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            updateSubResponseJFrame.setVisible(true);
            
        });
        buttonsPanel.add(updateButton, gbc);

        gbc.gridx = 1;
        JButton listButton = new JButton("listManager");
        listButton.setPreferredSize(new Dimension(175, 75));
        listButton.addActionListener(e -> {
            ListSubResponsesJFrame listSubResponsesJFrame = new ListSubResponsesJFrame();
            listSubResponsesJFrame.setReturnActionListener(returnSetVisibleFunction(listSubResponsesJFrame));
            listSubResponsesJFrame.setLocationRelativeTo(buttonsPanel);
            setVisible(false);
            listSubResponsesJFrame.setVisible(true);
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
