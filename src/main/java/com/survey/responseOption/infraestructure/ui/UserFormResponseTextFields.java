package com.survey.responseOption.infraestructure.ui;

import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responsequestion.application.AddResponseQuestionUseCase;
import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.responsequestion.infrastructure.repository.ResponseQuestionRepository;

import javax.swing.*;
import java.awt.*;

public class UserFormResponseTextFields extends JPanel {
    private static final int TEXT_FIELD_COLUMNS = 20;
    private static final int PADDING = 5;

    private final JButton sendButton;
    private final JTextField responseField;

    private final ResponseQuestionService responseQuestionService;
    private final AddResponseQuestionUseCase addResponseQuestionUseCase;

    public UserFormResponseTextFields(JButton sendButton, ResponseOption responseOption) {
        this.sendButton = sendButton;
        this.responseQuestionService = new ResponseQuestionRepository();
        this.addResponseQuestionUseCase = new AddResponseQuestionUseCase(responseQuestionService);

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        this.responseField = new JTextField(TEXT_FIELD_COLUMNS);

        addComponents();
        setupSendButtonListener(responseOption);
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, PADDING, PADDING);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.weightx = 1;
        add(responseField, gbc);
    }

    private void setupSendButtonListener(ResponseOption responseOption) {
        sendButton.addActionListener(e -> {
            ResponseQuestion responseQuestion = new ResponseQuestion();
            responseQuestion.setResponse_id(responseOption.getId());
            responseQuestion.setResponse_text(responseField.getText());
            addResponseQuestionUseCase.execute(responseQuestion);
        });
    }
}