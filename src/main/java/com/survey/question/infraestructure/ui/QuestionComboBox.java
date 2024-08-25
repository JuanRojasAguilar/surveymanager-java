package com.survey.question.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.question.application.ShowAllQuestionsUseCase;
import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;
import com.survey.question.infraestructure.repository.QuestionRepository;

public class QuestionComboBox extends JPanel {
    private JComboBox<Question> questionComboBox;
    private ShowAllQuestionsUseCase showAllQuestionsUseCase;
    private QuestionService questionRepository = new QuestionRepository();
    
    public QuestionComboBox() {
        questionComboBox = new JComboBox<>();
        questionComboBox.setPreferredSize(new Dimension(120, 30));

        setLayout(new BorderLayout());

        add(questionComboBox, BorderLayout.CENTER);
    }

    public QuestionComboBox(ActionListener actionListenerComboBox) {
        questionComboBox = new JComboBox<>();
        questionComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(questionComboBox, BorderLayout.CENTER);
    }

    public void updateQuestions() {
        showAllQuestionsUseCase = new ShowAllQuestionsUseCase(questionRepository);
        List<Question> questions = showAllQuestionsUseCase.execute(10, 0).get();
        questionComboBox.removeAllItems();
        questions.forEach(question -> {
            questionComboBox.addItem(question);
        });

        if (questionComboBox.getItemCount() == 0) {
            questionComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        questionComboBox.setEnabled(swich);
        revalidate();
        repaint();
    }

    public void setSelectedQuestion(Question question) {
        questionComboBox.addItem(question);
        questionComboBox.setSelectedItem(question);
    }

    public Question getSelectedQuestion() {
        return (Question) questionComboBox.getSelectedItem();
    }

}
