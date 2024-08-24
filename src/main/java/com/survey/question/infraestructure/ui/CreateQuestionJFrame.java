package com.survey.question.infraestructure.ui;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.infraestructure.ui.ChapterComboBox;
import com.survey.question.application.AddQuestionUseCase;
import com.survey.question.application.ShowAllQuestionsUseCase;
import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;
import com.survey.question.infraestructure.repository.QuestionRepository;
import com.survey.ui.StyleDefiner;

public class CreateQuestionJFrame extends JFrame {
    // catalog initializer
    private AddQuestionUseCase addQuestionUseCase;
    private ShowAllQuestionsUseCase showAllQuestionsUseCase;
    private QuestionService questionService = new QuestionRepository();

    private JButton returnButton;

    private JComboBox<String> responseType;
    private ChapterComboBox chapterComboBox;
    private JTextField commentField;
    private JTextField questionField;

    public CreateQuestionJFrame() {
        initComponents();
        createCreateFrame();
    }

    private void initComponents() {
        chapterComboBox = new ChapterComboBox();
        responseType = new JComboBox<>();
        responseType.addItem("seleccion multiple");
        responseType.addItem("seleccion");
        responseType.addItem("escrita");
    }

    private void createCreateFrame() {
        setTitle("Create Question");
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
        JLabel comboBoxLabel = new JLabel("Chapter");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        chapterComboBox.updateChapters();
        formPanel.add(chapterComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel typeLabel = new JLabel("tipo: ");
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(responseType, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel commentLabel = new JLabel("comentario: ");
        formPanel.add(commentLabel, gbc);

        gbc.gridx = 1;
        commentField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(commentField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel questionLabel = new JLabel("pregunta: ");
        formPanel.add(questionLabel, gbc);

        gbc.gridx = 1;
        questionField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(questionField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        createButton.addActionListener(guardarQuestion());
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener guardarQuestion() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addQuestionUseCase = new AddQuestionUseCase(questionService);
                showAllQuestionsUseCase = new ShowAllQuestionsUseCase(questionService);

                String comment = commentField.getText();
                String questionText = questionField.getText();
                String type = (String) responseType.getSelectedItem();
                Chapter chapter = chapterComboBox.getSelectedChapter();

                if (questionText.isEmpty()) {
                    JOptionPane.showMessageDialog(questionField, "campos incompletos", "error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                commentField.setText("");
                questionField.setText("");

                List<Question> questions = showAllQuestionsUseCase.execute(10, 0).get();
                List<Integer> questionNumber = new ArrayList<>();

                questions.forEach(question -> {
                    if (question.getIdChapter() == chapter.getId()) {
                        questionNumber.add(question.getId());
                    }
                });

                Question question = new Question();
                question.setQuestionText(questionText);
                question.setQuestionNumber(String.valueOf(questionNumber.size() + 1));
                question.setIdChapter(chapter.getId());
                question.setResponseType(type);
                question.setCommentQuestion(comment);

                addQuestionUseCase.execute(question);

                JOptionPane.showMessageDialog(questionField, "question guardado", "accion completada",
                        JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }

}