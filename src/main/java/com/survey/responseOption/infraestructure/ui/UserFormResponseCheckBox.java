package com.survey.responseOption.infraestructure.ui;

import com.survey.catalog.application.SearchCatalogByIdUseCase;
import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.domain.service.CatalogService;
import com.survey.catalog.infraestructure.repository.CatalogRepository;
import com.survey.responseOption.domain.entity.ResponseOption;
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

public class UserFormResponseCheckBox extends JPanel {
    private static final int PADDING = 5;

    private final JButton sendButton;
    private final JCheckBox checkBox;
    private final JPanel subResponsePanel;

    private final SubresponseOptionService subresponseOptionService;
    private final CatalogService catalogService;
    private final ResponseQuestionService responseQuestionService;

    private final ShowAllSubresponseOptionsUseCase showAllSubresponseOptionsUseCase;
    private final SearchCatalogByIdUseCase searchCatalogByIdUseCase;
    private final AddResponseQuestionUseCase addResponseQuestionUseCase;

    public UserFormResponseCheckBox(ResponseOption response, JButton sendButton) {
        this.sendButton = sendButton;
        this.subResponsePanel = new JPanel();

        this.subresponseOptionService = new SubresponseOptionRepository();
        this.catalogService = new CatalogRepository();
        this.responseQuestionService = new ResponseQuestionRepository();

        this.showAllSubresponseOptionsUseCase = new ShowAllSubresponseOptionsUseCase(subresponseOptionService);
        this.searchCatalogByIdUseCase = new SearchCatalogByIdUseCase(catalogService);
        this.addResponseQuestionUseCase = new AddResponseQuestionUseCase(responseQuestionService);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));

        this.checkBox = createCheckBox(response);
        add(checkBox);
        add(Box.createVerticalStrut(PADDING));

        setupSubResponsePanel();
        add(subResponsePanel);

        setupCheckBoxListener(response);
    }

    private JCheckBox createCheckBox(ResponseOption response) {
        String checkBoxText = getCheckBoxText(response);
        JCheckBox checkBox = new JCheckBox(checkBoxText);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
        return checkBox;
    }

    private String getCheckBoxText(ResponseOption response) {
        if (response.getIdCategoryCatalog() != 0) {
            Catalog catalog = searchCatalogByIdUseCase.execute(response.getIdCategoryCatalog()).get();
            return MessageFormat.format("{0} - {1}", catalog.getName(), response.getOptionText());
        }
        return response.getOptionText();
    }

    private void setupSubResponsePanel() {
        subResponsePanel.setLayout(new BoxLayout(subResponsePanel, BoxLayout.Y_AXIS));
        subResponsePanel.setVisible(false);
    }

    private void setupCheckBoxListener(ResponseOption response) {
        List<SubresponseOption> subresponses = showAllSubresponseOptionsUseCase.execute().get();
        boolean hasSubResponse = subresponses.stream()
                .anyMatch(subresponse -> response.getId() == subresponse.getIdResponseOption());

        checkBox.addItemListener(e -> handleCheckBoxStateChange(e, response, hasSubResponse, subresponses));
    }

    private void handleCheckBoxStateChange(ItemEvent e, ResponseOption response, boolean hasSubResponse, List<SubresponseOption> subresponses) {
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;

        if (!hasSubResponse) {
            updateSendButtonListener(response, isSelected);
        } else {
            subResponsePanel.setVisible(isSelected);
            if (isSelected) {
                populateSubResponsePanel(response, subresponses);
            }
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

    private void populateSubResponsePanel(ResponseOption response, List<SubresponseOption> subresponses) {
        subResponsePanel.removeAll();
        subresponses.stream()
                .filter(subresponse -> subresponse.getIdResponseOption() == response.getId())
                .forEach(subresponse -> addSubresponseComponent(response, subresponse));
    }

    private void addSubresponseComponent(ResponseOption response, SubresponseOption subresponse) {
        JComponent subresponseComponent = null;
        switch (response.getSubresponseType()) {
            case "seleccion multiple":
                subresponseComponent = createMultipleSelectionSubresponse(subresponse);
                break;
            case "seleccion":
                subresponseComponent = createSingleSelectionSubresponse(subresponse);
                break;
            case "escrita":
                subresponseComponent = createWrittenSubresponse(subresponse);
                break;
        }
        if (subresponseComponent != null) {
            subResponsePanel.add(subresponseComponent);
            subResponsePanel.add(Box.createVerticalStrut(PADDING));
        }
    }

    private JComponent createMultipleSelectionSubresponse(SubresponseOption subresponse) {
        // Implementar lógica para selección múltiple
        return new JLabel("Multiple Selection: " + subresponse.getSubresponseText());
    }

    private JComponent createSingleSelectionSubresponse(SubresponseOption subresponse) {
        // Implementar lógica para selección única
        return new JLabel("Single Selection: " + subresponse.getSubresponseText());
    }

    private JComponent createWrittenSubresponse(SubresponseOption subresponse) {
        // Implementar lógica para respuesta escrita
        return new JTextField(subresponse.getSubresponseText());
    }
}