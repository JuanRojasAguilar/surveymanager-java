package com.survey.responseOption.infraestructure.ui;

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

import com.survey.question.domain.entity.Question;
import com.survey.question.infraestructure.ui.QuestionComboBox;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.entity.ResponseOptionDTO;

public class ListResponsesJFrame extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private ResponseComboBox responseComboBox;
    private JButton returnButton;

    private boolean initializer;

    //initializer

    public ListResponsesJFrame() {
        initializer = true;

        initComponents();

        createListResponses();

        initializer = false;
    }

    private void initComponents() {
        responseComboBox = new ResponseComboBox(getSelectedResponses());
    }

    private void createListResponses() {
        setTitle("List Responses");
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
        JLabel comboBoxLabel = new JLabel("Response");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"id", "categoryCatalog", "parentResponse", "question", "optionText", "createdAt", "updateAt"};
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

        showAllResponses();

        add(formPanel, BorderLayout.CENTER);
    }

    private void showAllResponses() {
        //list from initializer

        responses.forEach(response -> {
            Object[] rowData = {response.getId(), response.getIdCategoryCatalog(), response.getIdParentResponse(), response.getIdQuestion(), response.getOptionText(), response.getCreateAt(), response.getUpdateAt()};
            model.addRow(rowData);
        });
    }

     private ActionListener getSelectedResponses() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    ResponseOption response = responseComboBox.getSelectedResponse();
                    model.setRowCount(0);
                    Object[] rowData = {response.getId(), response.getIdCategoryCatalog(), response.getIdParentResponse(), response.getIdQuestion(), response.getOptionText(), response.getCreateAt(), response.getUpdateAt()};
                    model.addRow(rowData);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}

