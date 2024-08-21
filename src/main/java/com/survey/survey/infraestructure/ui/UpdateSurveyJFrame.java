package com.survey.survey.infraestructure.ui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.ui.StyleDefiner;

public class UpdateSurveyJFrame extends JFrame {
    //surveyInitializer 

    private JButton returnButton;

    private SurveyComboBox surveyComboBox;
    private JTextField nombreField;
    private JTextField descField;
    private JButton updateButton;

    private boolean initializer;

    private int idToUpdate;

    public UpdateSurveyJFrame() {
        
        initializer = true;

        initComponents();

        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox(getSelectedSurvey());
    }

    private void createUpdateFrame() {
        setTitle("Update Survey");
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
        JLabel comboBoxLabel = new JLabel("survey");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel nombreLabel = new JLabel("nombre: ");
        formPanel.add(nombreLabel, gbc);

        gbc.gridx = 1;
        nombreField = StyleDefiner.defineFieldStyle(new JTextField(20));
        nombreField.setEditable(false);
        formPanel.add(nombreField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        JLabel descLabel = new JLabel("descripcion: ");
        formPanel.add(descLabel, gbc);
        
        gbc.gridx = 1;
        descField = StyleDefiner.defineFieldStyle(new JTextField(20));
        descField.setEditable(false);
        formPanel.add(descField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarSurvey());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarSurvey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String nombre = nombreField.getText();
                String desc = descField.getText();

                if (nombre.isEmpty() ||
                desc.isEmpty()) {
                    JOptionPane.showMessageDialog(nombreField, "campos incompletos", "erroe", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                //Survey survey = new Survey(nombre, desc);

                //initializer

                JOptionPane.showMessageDialog(nombreField, "usuario actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    private ActionListener getSelectedSurvey() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    //Survey survey = surveyComboBox.getSelectedSurvey();
                    nombreField.setText(survey.getName());
                    descField.setText(survey.getDesc());
                    nombreField.setEditable(true);
                    descField.setEditable(true);
                    updateButton.setEnabled(true);


                    idToUpdate = survey.getId();
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
