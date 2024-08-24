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

import com.survey.survey.domain.entity.Survey;
import com.survey.chapter.application.AddChapterUseCase;
import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.repository.ChapterRepository;
import com.survey.survey.infraestructure.ui.SurveyComboBox;
import com.survey.ui.StyleDefiner;

public class CreateChapterJFrame extends JFrame{
    private ChapterService chapterService = new ChapterRepository();
    private AddChapterUseCase addChapterUseCase;
    private ShowAllChaptersUseCase showAllChaptersUseCase;

    private JButton returnButton;

    private JTextField titleField;
    private SurveyComboBox surveyComboBox;

    public CreateChapterJFrame() {
        initComponents();
        createCreateFrame();
    }

    private void initComponents() {
        surveyComboBox = new SurveyComboBox();
    }

    private void createCreateFrame() {
        setTitle("Create Chapter");
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
        JLabel comboBoxLabel = new JLabel("survey");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel tituloLabel = new JLabel("titulo: ");
        formPanel.add(tituloLabel, gbc);

        gbc.gridx = 1;
        titleField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(titleField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        createButton.addActionListener(guardarChapter());
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener guardarChapter() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addChapterUseCase = new AddChapterUseCase(chapterService);
                showAllChaptersUseCase = new ShowAllChaptersUseCase(chapterService);

                String title = titleField.getText();
                Survey survey = surveyComboBox.getSelectedSurvey();

                if (title.isEmpty()) {
                    JOptionPane.showMessageDialog(titleField, "campos incompletos", "erroe", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                titleField.setText("");

                List<Chapter> chapters = showAllChaptersUseCase.execute(10, 0).get();

                List<Integer> numeroDeChapters = new ArrayList<>();
                
                chapters.forEach(capitulo -> {
                    if (capitulo.getId() == survey.getId()) {
                        numeroDeChapters.add(capitulo.getId());
                    }
                });

                Chapter chapter = new Chapter();
                chapter.setChapterTitle(title);
                chapter.setChapterNumber(String.valueOf(numeroDeChapters.size() + 1));
                chapter.setIdSurvey(survey.getId());

                addChapterUseCase.execute(chapter);
                JOptionPane.showMessageDialog(titleField, "chapter guardado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
            
        };   
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
