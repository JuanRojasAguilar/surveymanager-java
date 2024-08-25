package com.survey.survey.infraestructure.ui;

import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.repository.ChapterRepository;
import com.survey.chapter.infraestructure.ui.UserFormChapter;
import com.survey.ui.StyleDefiner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class UserForm extends JFrame {
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    private static final int PADDING = 5;

    private final SurveyComboBox surveyComboBox;
    private final JButton returnButton;
    private final JPanel formPanel;
    private final JButton sendButton;
    private final ChapterService chapterService;
    private ShowAllChaptersUseCase showAllChaptersUseCase;

    public UserForm() {
        chapterService = new ChapterRepository();
        surveyComboBox = new SurveyComboBox();
        returnButton = new JButton("<--");
        formPanel = new JPanel(new GridBagLayout());
        sendButton = StyleDefiner.defineButtonStyle(new JButton("Send"));
        sendButton.addActionListener(e -> {
            clearFormPanel();
        });

        initializeUI();
    }

    private void initializeUI() {
        setTitle("User Form");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        add(createTopPanel(), BorderLayout.NORTH);
        add(createScrollableFormPanel(), BorderLayout.CENTER);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(returnButton);
        return topPanel;
    }

    private JScrollPane createScrollableFormPanel() {
        setupFormPanel();
        return new JScrollPane(formPanel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void setupFormPanel() {
        GridBagConstraints gbc = createGridBagConstraints();

        surveyComboBox.updateSurveys();
        formPanel.add(surveyComboBox, gbc);

        gbc.gridx = 1;
        JButton confirmButton = new JButton("Confirm");
        formPanel.add(confirmButton, gbc);

        confirmButton.addActionListener(e -> updateFormWithChapters());
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(PADDING, PADDING, PADDING, PADDING);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }

    private void updateFormWithChapters() {
        clearFormPanel();
        List<Chapter> surveyChapters = getChaptersForSelectedSurvey();
        addChaptersToForm(surveyChapters);
        addSendButton();
        revalidate();
        repaint();
    }

    private void clearFormPanel() {
        for (int i = formPanel.getComponentCount() - 1; i > 1; i--) {
            formPanel.remove(i);
        }
    }

    private List<Chapter> getChaptersForSelectedSurvey() {
        showAllChaptersUseCase = new ShowAllChaptersUseCase(chapterService);
        List<Chapter> allChapters = showAllChaptersUseCase.execute().get();
        int selectedSurveyId = surveyComboBox.getSelectedSurvey().getId();
        
        return allChapters.stream()
                .filter(chapter -> chapter.getIdSurvey() == selectedSurveyId)
                .collect(Collectors.toList());
    }

    private void addChaptersToForm(List<Chapter> chapters) {
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;

        int row = 1;
        for (Chapter chapter : chapters) {
            gbc.gridy = ++row;
            formPanel.add(new UserFormChapter(sendButton, chapter), gbc);
        }
    }

    private void addSendButton() {
        GridBagConstraints gbc = createGridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridy = formPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(sendButton, gbc);
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }


}