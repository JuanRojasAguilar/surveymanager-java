package com.survey.question.infraestructure.ui;

import com.survey.question.domain.entity.Question;
import com.survey.responseOption.application.ShowAllResponseOptionsUseCase;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;
import com.survey.responseOption.infraestructure.repository.ResponseOptionRepository;
import com.survey.responseOption.infraestructure.ui.UserFormResponseJRadioButton;
import com.survey.responseOption.infraestructure.ui.UserFormResponseTextFields;
import com.survey.responseOption.infraestructure.ui.UserFormResponseCheckBox;
import com.survey.responsequestion.application.AddResponseQuestionUseCase;
import com.survey.responsequestion.domain.entity.ResponseQuestion;
import com.survey.responsequestion.domain.service.ResponseQuestionService;
import com.survey.responsequestion.infrastructure.repository.ResponseQuestionRepository;
import com.survey.subresponseOption.application.ShowAllSubresponseOptionsUseCase;
import com.survey.subresponseOption.domain.entity.SubresponseOption;
import com.survey.subresponseOption.domain.service.SubresponseOptionService;
import com.survey.subresponseOption.infraestructure.repository.SubresponseOptionRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.text.MessageFormat;
import java.util.List;

public class UserFormQuestion extends JPanel {
    private static final int PADDING = 10;

    private final JButton sendButton;
    private final ResponseOptionService responseOptionService;
    private final SubresponseOptionService subresponseOptionService;
    private final ResponseQuestionService responseQuestionService;
    private final ShowAllResponseOptionsUseCase showAllResponseOptionsUseCase;
    private final ShowAllSubresponseOptionsUseCase showAllSubresponseOptionsUseCase;
    private final AddResponseQuestionUseCase addResponseQuestionUseCase;

    public UserFormQuestion(JButton sendButton, Question question) {
        this.sendButton = sendButton;
        this.responseOptionService = new ResponseOptionRepository();
        this.subresponseOptionService = new SubresponseOptionRepository();
        this.responseQuestionService = new ResponseQuestionRepository();

        this.showAllResponseOptionsUseCase = new ShowAllResponseOptionsUseCase(responseOptionService);
        this.showAllSubresponseOptionsUseCase = new ShowAllSubresponseOptionsUseCase(subresponseOptionService);
        this.addResponseQuestionUseCase = new AddResponseQuestionUseCase(responseQuestionService);

        initializeUI(question);
    }

    private void initializeUI(Question question) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        addQuestionLabels(question);
        addResponseOptions(question);
    }

    private void addQuestionLabels(Question question) {
        if (question.getCommentQuestion() != null) {
            JLabel commentLabel = new JLabel(question.getCommentQuestion());
            commentLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            add(commentLabel);
            add(Box.createVerticalStrut(PADDING / 2));
        }

        JLabel questionLabel = new JLabel(formatQuestionText(question));
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(questionLabel);
        add(Box.createVerticalStrut(PADDING));
    }

    private String formatQuestionText(Question question) {
        return MessageFormat.format("{0}. {1}", question.getQuestionNumber(), question.getQuestionText());
    }

    private void addResponseOptions(Question question) {
        List<ResponseOption> responses = showAllResponseOptionsUseCase.execute().get();
        List<SubresponseOption> subresponses = showAllSubresponseOptionsUseCase.execute().get();

        ButtonGroup buttonGroup = new ButtonGroup();

        responses.stream()
                 .filter(response -> response.getIdQuestion() == question.getId())
                 .forEach(response -> addResponseOption(question, response, subresponses, buttonGroup));
    }

    private void addResponseOption(Question question, ResponseOption response, List<SubresponseOption> subresponses, ButtonGroup buttonGroup) {
        switch (question.getResponseType()) {
            case "seleccion multiple":
                addMultipleSelectionOption(response);
                break;
            case "seleccion":
                addSingleSelectionOption(response, subresponses, buttonGroup);
                break;
            case "escrita":
                addWrittenOption(response);
                break;
        }
    }

    private void addMultipleSelectionOption(ResponseOption response) {
        if (response.getIdParentResponse() == 0) {
            add(new UserFormResponseCheckBox(response, sendButton));
        }
    }

    private void addSingleSelectionOption(ResponseOption response, List<SubresponseOption> subresponses, ButtonGroup buttonGroup) {
        JPanel subresponsePanel = new JPanel();
        subresponsePanel.setLayout(new BoxLayout(subresponsePanel, BoxLayout.Y_AXIS));

        boolean hasSubResponse = subresponses.stream()
                .anyMatch(subresponse -> response.getId() == subresponse.getIdResponseOption());

        JRadioButton radioButton = createRadioButton(response, subresponsePanel, hasSubResponse);
        buttonGroup.add(radioButton);
        add(radioButton);

        if (hasSubResponse) {
            addSubresponses(response, subresponses, subresponsePanel);
        }

        add(subresponsePanel);
        add(Box.createVerticalStrut(PADDING / 2));
    }

    private JRadioButton createRadioButton(ResponseOption response, JPanel subresponsePanel, boolean hasSubResponse) {
        return new UserFormResponseJRadioButton(response, e -> {
            boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
            handleRadioButtonSelection(response, subresponsePanel, hasSubResponse, isSelected);
        });
    }

    private void handleRadioButtonSelection(ResponseOption response, JPanel subresponsePanel, boolean hasSubResponse, boolean isSelected) {
        if (!hasSubResponse) {
            updateSendButtonListener(response, isSelected);
        } else {
            subresponsePanel.setVisible(isSelected);
        }
        revalidate();
        repaint();
    }

    private void updateSendButtonListener(ResponseOption response, boolean isSelected) {
        if (isSelected) {
            sendButton.addActionListener(e -> addResponseQuestion(response));
        } else {
            for (ActionListener al : sendButton.getActionListeners()) {
                sendButton.removeActionListener(al);
            }
        }
    }

    private void addResponseQuestion(ResponseOption response) {
        ResponseQuestion responseQuestion = new ResponseQuestion();
        responseQuestion.setResponse_id(response.getId());
        responseQuestion.setResponse_text(response.getOptionText());
        addResponseQuestionUseCase.execute(responseQuestion);
    }

    private void addSubresponses(ResponseOption response, List<SubresponseOption> subresponses, JPanel subresponsePanel) {
        subresponses.stream()
                .filter(subresponse -> subresponse.getIdResponseOption() == response.getId())
                .forEach(subresponse -> addSubresponse(response, subresponse, subresponsePanel));
    }

    private void addSubresponse(ResponseOption response, SubresponseOption subresponse, JPanel subresponsePanel) {
        switch (response.getSubresponseType()) {
            case "seleccion multiple":
                // Implementar lógica para selección múltiple
                break;
            case "seleccion":
                // Implementar lógica para selección única
                break;
            case "escrita":
                // Implementar lógica para respuesta escrita
                break;
        }
    }

    private void addWrittenOption(ResponseOption response) {
        add(new UserFormResponseTextFields(sendButton, response));
        add(Box.createVerticalStrut(PADDING / 2));
    }
}