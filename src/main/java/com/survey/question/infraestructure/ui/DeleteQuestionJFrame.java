package com.survey.question.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.survey.question.domain.entity.Question;
import com.survey.ui.StyleDefiner;

public class DeleteQuestionJFrame extends JFrame {
    private QuestionComboBox questionComboBox;
    private JButton returnButton;

    //initializer

    public DeleteQuestionJFrame() {
        initComponents();

        createDeleteQuestion();
    }

    private void initComponents() {
        questionComboBox = new QuestionComboBox();
    }

    private void createDeleteQuestion() {
        setTitle("Delete Question");
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
        formPanel.add(questionComboBox);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            Question question = questionComboBox.getSelectedQuestion();

            int continuar = JOptionPane.showConfirmDialog(questionComboBox, "seguro que quieres eliminar a este question?", "¿?", JOptionPane.YES_NO_OPTION);

            if (continuar == 0) {
                // initializer del delete
                JOptionPane.showMessageDialog(null, "eliminado correctamente", "eliminado", JOptionPane.INFORMATION_MESSAGE);
                questionComboBox.updateQuestions();
            }
        });
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
