package com.survey.chapter.infraestructure.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.text.MessageFormat;
import java.util.List;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.question.application.ShowAllQuestionsUseCase;
import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;

public class UserFormChapter extends JPanel {
    private JButton sendButton;
    private QuestionService questionService;
    private ShowAllQuestionsUseCase showAllQuestionsUseCase;

    public UserFormChapter(JButton sendButton, 
    Chapter chapter) {
        this.sendButton = sendButton;
    
        showAllQuestionsUseCase = new ShowAllQuestionsUseCase(questionService);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel chapterLabel = new JLabel(MessageFormat.format("{0}, {1}", chapter.getChapterNumber(), chapter.getChapterTitle()));

        add(chapterLabel);

        List<Question> questions = showAllQuestionsUseCase.execute(20, 0).get();

        questions.forEach(question -> {
            if (question.getIdChapter() == chapter.getId()) {
                
            }
        });
    }
}
