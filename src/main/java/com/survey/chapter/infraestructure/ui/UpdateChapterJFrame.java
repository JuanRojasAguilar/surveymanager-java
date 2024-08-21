package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.survey.infraestructure.ui.SurveyComboBox;
import com.survey.ui.StyleDefiner;

public class UpdateChapterJFrame extends JFrame{
        private JButton returnButton;

        private ChapterComboBox chapterComboBox;
        private JTextField titleField;
        private SurveyComboBox surveyComboBox;
        private JButton updateButton;

        private boolean initializer;

        private Chapter chapterToEdit;

    public UpdateChapterJFrame() {
        initializer = true;

        initComponents();

        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        ChapterComboBox chapterComboBox = new ChapterComboBox(getSelectedChapter());
    }

    private void createUpdateFrame() {
        setTitle("Update chapter");
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
        JLabel comboBoxLabel = new JLabel("chapter");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        chapterComboBox.updateChapters();
        formPanel.add(chapterComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxSurveyLabel = new JLabel("survey");
        formPanel.add(comboBoxSurveyLabel, gbc);

        gbc.gridx = 1;
        surveyComboBox.updateSurveys();
        surveyComboBox.switcher(false);
        formPanel.add(surveyComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel titleJLabel = new JLabel("titulo: ");
        formPanel.add(titleJLabel, gbc);

        gbc.gridx = 1;
        titleField = StyleDefiner.defineFieldStyle(new JTextField(20));
        titleField.setEditable(false);
        formPanel.add(titleField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("actualizar"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarChapter());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarChapter() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                    String title = titleField.getText();
                    Survey survey = surveyComboBox.getSelectedSurvey();

                    if (title.isEmpty()) {
                        JOptionPane.showMessageDialog(titleField, "campos incompletos", "erroe", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    titleField.setText("");

                    // initializer trae todos los chapters

                    List<Chapter> numeroDeChapters = chapters.stream()
                                                            .filter(chapter.getIdSurvey() == survey.getId())
                                                            .toArray();

                    chapterToEdit.setChapterTitle(title);
                    chapterToEdit.setIdSurvey(survey.getId());

                    //initializer

                    JOptionPane.showMessageDialog(titleField, "chapter actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    private ActionListener getSelectedChapter() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    chapterToEdit = chapterComboBox.getSelectedChapter();
                    // logica para obtener el survey de este chapter
                    titleField.setText(chapterToEdit.getChapterTitle());
                    titleField.setEditable(true);
                    surveyComboBox.setSelectedSurvey(survey);
                    surveyComboBox.switcher(true);

                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
