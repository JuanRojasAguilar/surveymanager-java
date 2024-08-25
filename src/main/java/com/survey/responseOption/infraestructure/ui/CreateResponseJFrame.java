package com.survey.responseOption.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.catalog.infraestructure.ui.CatalogComboBox;
import com.survey.question.domain.entity.Question;
import com.survey.question.infraestructure.ui.QuestionComboBox;
import com.survey.responseOption.application.AddResponseOptionUseCase;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.responseOption.domain.service.ResponseOptionService;
import com.survey.responseOption.infraestructure.repository.ResponseOptionRepository;
import com.survey.ui.StyleDefiner;

public class CreateResponseJFrame extends JFrame{
    private ResponseOptionService responseOptionService = new ResponseOptionRepository();
    private AddResponseOptionUseCase addResponseOptionUseCase;

    private JButton returnButton;

    private JComboBox<String> subResponseType;
    private ResponseComboBox responseComboBox;
    private QuestionComboBox questionComboBox;
    private CatalogComboBox catalogComboBox; 
    private JTextField commentField;
    private JTextField optionnField;

    public CreateResponseJFrame() {
        initComponents();
        createCreateFrame();
    }

    private void initComponents() {
        responseComboBox = new ResponseComboBox();
        questionComboBox = new QuestionComboBox();
        catalogComboBox = new CatalogComboBox();
        subResponseType = new JComboBox<>();
        subResponseType.addItem("seleccion multiple");
        subResponseType.addItem("seleccion");
        subResponseType.addItem("escrita");
    }

    private void createCreateFrame() {
        setTitle("Create Response");
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
        JLabel comboBoxLabelQuestion = new JLabel("Question: ");
        formPanel.add(comboBoxLabelQuestion, gbc);

        gbc.gridx = 1;
        questionComboBox.updateQuestions();
        formPanel.add(questionComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel catalogLabel = new JLabel("Catalog: ");
        formPanel.add(catalogLabel, gbc);

        gbc.gridx = 1;
        catalogComboBox.updateCatalogs();
        formPanel.add(catalogComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel responseLabel = new JLabel("ResponseParent: ");
        formPanel.add(responseLabel, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel typeLabel = new JLabel("tipo: ");
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(subResponseType, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel commentLabel = new JLabel("comment: ");
        formPanel.add(commentLabel, gbc);

        gbc.gridx = 1;
        commentField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(commentField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel optionLabel = new JLabel("option: ");
        formPanel.add(optionLabel, gbc);

        gbc.gridx = 1;
        optionnField = StyleDefiner.defineFieldStyle(new JTextField(20));
        formPanel.add(optionnField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = StyleDefiner.defineButtonStyle(new JButton("crear"));
        createButton.addActionListener(guardarResponse());
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener guardarResponse() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                addResponseOptionUseCase = new AddResponseOptionUseCase(responseOptionService);

                String comment = commentField.getText();
                String option = optionnField.getText();
                ResponseOption response = responseComboBox.isActive() ? responseComboBox.getSelectedResponse() : null;
                String type = (String) subResponseType.getSelectedItem();
                Question question = questionComboBox.getSelectedQuestion();
                Catalog catalog = catalogComboBox.isActive() ? catalogComboBox.getSelectedCatalog() : null;

                if (comment.isEmpty() || option.isEmpty()) {
                    JOptionPane.showMessageDialog(commentField, "campos incompletos", "error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                commentField.setText("");
                optionnField.setText("");

                ResponseOption responseOption = new ResponseOption();
                responseOption.setCommentResponse(comment);
                responseOption.setOptionText(option);
                responseOption.setSubresponseType(type);
                if (catalog != null) {responseOption.setIdCategoryCatalog(catalog.getId());}
                if (response != null) {responseOption.setIdParentResponse(response.getId());}
                responseOption.setIdQuestion(question.getId());
                if (responseOption.getIdQuestion() != response.getIdQuestion()) {
                    JOptionPane.showMessageDialog(commentField, "la response y la response parent tienen que pertenecer a la misma pregunta", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (question.getResponseType().equals("seleccion") || question.getResponseType().equals("escrita")) {
                    JOptionPane.showMessageDialog(commentField, "el response type de esta pregunta debe ser de seleccion", "error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                addResponseOptionUseCase.execute(responseOption);

                JOptionPane.showMessageDialog(commentField, "response guardado", "accion completada", JOptionPane.WARNING_MESSAGE);
            } 
        };   
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
