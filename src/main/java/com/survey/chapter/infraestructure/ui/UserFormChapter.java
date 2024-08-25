package com.survey.chapter.infraestructure.ui;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.question.application.ShowAllQuestionsUseCase;
import com.survey.question.domain.entity.Question;
import com.survey.question.domain.service.QuestionService;
import com.survey.question.infraestructure.repository.QuestionRepository;
import com.survey.question.infraestructure.ui.UserFormQuestion;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.List;

public class UserFormChapter extends JPanel {
    private static final int PADDING = 10;
    private static final int QUESTIONS_LIMIT = 20;
    private static final int QUESTIONS_OFFSET = 0;

    private final JButton sendButton;
    private final QuestionService questionService;
    private final ShowAllQuestionsUseCase showAllQuestionsUseCase;

    public UserFormChapter(JButton sendButton, Chapter chapter) {
        this.sendButton = sendButton;
        this.questionService = new QuestionRepository();
        this.showAllQuestionsUseCase = new ShowAllQuestionsUseCase(questionService);

        initializeUI(chapter);
    }

    private void initializeUI(Chapter chapter) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        addChapterTitle(chapter);
        addQuestionsForChapter(chapter);
    }

    private void addChapterTitle(Chapter chapter) {
        JLabel chapterLabel = new JLabel(formatChapterTitle(chapter));
        chapterLabel.setFont(new Font("Arial", Font.BOLD, 14));
        chapterLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(chapterLabel);
        add(Box.createVerticalStrut(PADDING));
    }

    private String formatChapterTitle(Chapter chapter) {
        return MessageFormat.format("{0}. {1}", chapter.getChapterNumber(), chapter.getChapterTitle());
    }

    private void addQuestionsForChapter(Chapter chapter) {
        List<Question> questions = showAllQuestionsUseCase.execute(QUESTIONS_LIMIT, QUESTIONS_OFFSET).get();
        questions.stream()
                 .filter(question -> question.getIdChapter() == chapter.getId())
                 .forEach(this::addQuestionToPanel);
    }

    private void addQuestionToPanel(Question question) {
        UserFormQuestion userFormQuestion = new UserFormQuestion(sendButton, question);
        userFormQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(userFormQuestion);
        add(Box.createVerticalStrut(PADDING / 2));
    }
}