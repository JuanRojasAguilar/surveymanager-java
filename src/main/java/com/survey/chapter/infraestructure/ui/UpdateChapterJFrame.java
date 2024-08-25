package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.application.UpdateChapterUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.repository.ChapterRepository;
import com.survey.survey.application.SearchSurveyByIdUseCase;
import com.survey.survey.domain.entity.Survey;
import com.survey.survey.domain.service.SurveyService;
import com.survey.survey.infraestructure.repository.SurveyRepository;
import com.survey.survey.infraestructure.ui.SurveyComboBox;
import com.survey.ui.StyleDefiner;

public class UpdateChapterJFrame extends JFrame {
    private JButton returnButton;

    private ChapterComboBox chapterComboBox;
    private JTextField titleField;
    private SurveyComboBox surveyComboBox;
    private JButton updateButton;
    private ShowAllChaptersUseCase showAllChaptersUseCase;
    private UpdateChapterUseCase updateChapterUseCase;
    private ChapterService chapterService = new ChapterRepository();
    private SearchSurveyByIdUseCase searchSurveyByIdUseCase;
    private SurveyService surveyService = new SurveyRepository();

    private boolean initializer;

    private Chapter chapterToEdit;

    public UpdateChapterJFrame() {
        initializer = true;

        initComponents();

        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        chapterComboBox = new ChapterComboBox(getSelectedChapter());
        surveyComboBox = new SurveyComboBox();
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
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
                showAllChaptersUseCase = new ShowAllChaptersUseCase(chapterService);
                updateChapterUseCase = new UpdateChapterUseCase(chapterService);

                String title = titleField.getText();
                Survey survey = surveyComboBox.getSelectedSurvey();

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(titleField, "campos incompletos", "erroe",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                titleField.setText("");

                List<Chapter> chapters = showAllChaptersUseCase.execute().get();

                List<Integer> numeroDeChapters = new ArrayList<>();

                chapters.forEach(capitulo -> {
                    if (capitulo.getId() == survey.getId()) {
                        numeroDeChapters.add(capitulo.getId());
                    }
                });

                chapterToEdit.setChapterTitle(title);
                chapterToEdit.setIdSurvey(survey.getId());

                updateChapterUseCase.execute(chapterToEdit);

                JOptionPane.showMessageDialog(titleField, "chapter actualizado", "accion completada",
                        JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    private ActionListener getSelectedChapter() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                searchSurveyByIdUseCase = new SearchSurveyByIdUseCase(surveyService);

                if (!initializer) {
                    chapterToEdit = chapterComboBox.getSelectedChapter();
                    Survey survey = searchSurveyByIdUseCase.execute(chapterToEdit.getIdSurvey()).get();
                    titleField.setText(chapterToEdit.getChapterTitle());
                    titleField.setEditable(true);
                    surveyComboBox.setSelectedSurvey(survey);
                    surveyComboBox.switcher(true);
                    updateButton.setEnabled(true);
                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
