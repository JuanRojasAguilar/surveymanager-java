package com.survey.subresponseOption.infraestructure.ui;

import java.awt.BorderLayout;
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

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.infraestructure.ui.ResponseComboBox;
import com.survey.subresponseOption.domain.entity.SubresponseOption;

public class ListSubResponsesJFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    private SubResponseComboBox subResponseComboBox;
    private JButton returnButton;

    private boolean initializer;

    //initializer

    public ListSubResponsesJFrame() {
        initializer = true;

        initComponents();

        createListResponses();

        initializer = false;
    }

    private void initComponents() {
        subResponseComboBox = new SubResponseComboBox(getSelectedSubResponse());
    }

    private void createListResponses() {
        setTitle("List SubResponses");
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
        JLabel comboBoxLabel = new JLabel("SubResponse");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        subResponseComboBox.updateSubResponses();
        formPanel.add(subResponseComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames = {"id", "ResponseOptions", "subResponse", "createdAt", "updateAt"};
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

        showAllSubResponses();

        add(formPanel, BorderLayout.CENTER);
    }

    private void showAllSubResponses() {
        // initializar

        subResponses.forEach(subResponse -> {
            Object[] rowData = {response.getId(), response.getIdCategoryCatalog(), response.getIdParentResponse(), response.getIdQuestion(), response.getOptionText(), response.getCreateAt(), response.getUpdateAt()};
            model.addRow(rowData);
        });
    }

    private ActionListener getSelectedSubResponse() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    SubresponseOption subResponse = subResponseComboBox.getSelectedSubResponse();
                    model.setRowCount(0);
                    Object[] rowData = {subResponse.getId(), subResponse.getIdResponseOptions(), subResponse.getSubresponseText(), subResponse.getCreatedAt(), subResponse.getUpdatedAt()};
                    model.addRow(rowData);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
