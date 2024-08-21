package com.survey.question.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.question.domain.entity.Question;

public class QuestionComboBox extends JPanel {
    private JComboBox<Question> questionComboBox;
    
    public QuestionComboBox() {
        questionComboBox = new JComboBox<>();

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
        List<Question> questions = //InitializerChapter 
        questionComboBox.removeAllItems();
        questions.forEach(question -> {
            questionComboBox.addItem(question);
        });

        if (questionComboBox.getItemCount() == 0) {
            questionComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        questionComboBox.setEditable(swich);
    }

    public void setSelectedQuestion(Question question) {
        questionComboBox.addItem(question);
        questionComboBox.setSelectedItem(question);
    }

    public Question getSelectedQuestion() {
        return (Question) questionComboBox.getSelectedItem();
    }

}
