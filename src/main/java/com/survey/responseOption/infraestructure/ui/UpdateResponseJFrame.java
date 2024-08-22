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
import com.survey.chapter.infraestructure.ui.ChapterComboBox;
import com.survey.question.domain.entity.Question;
import com.survey.question.infraestructure.ui.QuestionComboBox;
import com.survey.responseOption.domain.entity.ResponseOption;
import com.survey.ui.StyleDefiner;

public class UpdateResponseJFrame extends JFrame{
    //initializer de question
    private JButton returnButton;

    private ResponseComboBox responseComboBoxMain;
    private ResponseComboBox responseComboBox;
    private QuestionComboBox questionComboBox;
    private CatalogComboBox catalogComboBox; 
    private JTextField commentField;
    private JTextField optionnField;
    private JButton updateButton;

    private boolean initializer;
    private ResponseOption responseToEdit;

    public UpdateResponseJFrame() {
        initializer = true;

        initComponents();
        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        responseComboBoxMain = new ResponseComboBox(getSelectedResponse());
        responseComboBox = new ResponseComboBox();
        questionComboBox = new QuestionComboBox();
        catalogComboBox = new CatalogComboBox();
    }

    private void createUpdateFrame() {
        setTitle("Update Response");
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
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabel = new JLabel("response");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        responseComboBoxMain.updateResponses();
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabelQuestion = new JLabel("Question: ");
        formPanel.add(comboBoxLabelQuestion, gbc);

        gbc.gridx = 1;
        questionComboBox.updateQuestions();
        questionComboBox.switcher(false);
        formPanel.add(questionComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel catalogLabel = new JLabel("Catalog: ");
        formPanel.add(catalogLabel, gbc);

        gbc.gridx = 1;
        catalogComboBox.updateCatalogs();
        questionComboBox.switcher(false);
        formPanel.add(catalogComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel responseLabel = new JLabel("ResponseParent: ");
        formPanel.add(responseLabel, gbc);

        gbc.gridx = 1;
        responseComboBox.updateResponses();
        responseComboBox.switcher(false);
        formPanel.add(responseComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel commentLabel = new JLabel("comment: ");
        formPanel.add(commentLabel, gbc);

        gbc.gridx = 1;
        commentField = StyleDefiner.defineFieldStyle(commentField);
        commentField.setEditable(false);
        formPanel.add(commentField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel optionLabel = new JLabel("option: ");
        formPanel.add(optionLabel, gbc);

        gbc.gridx = 1;
        optionnField = StyleDefiner.defineFieldStyle(optionnField);
        optionnField.setEditable(false);
        formPanel.add(optionnField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("actualizar"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarResponse());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarResponse() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String comment = commentField.getText();
                String option = optionnField.getText();
                ResponseOption response = responseComboBox.isActive() ? responseComboBox.getSelectedResponse() : null;
                Question question = questionComboBox.getSelectedQuestion();
                Catalog catalog = catalogComboBox.getSelectedCatalog();

                if (comment.isEmpty() || option.isEmpty()) {
                    JOptionPane.showMessageDialog(commentField, "campos incompletos", "error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                commentField.setText("");
                optionnField.setText("");

                ResponseOption responseOption = new ResponseOption();
                responseOption.setCommentResponse(comment);
                responseOption.setOptionText(option);
                responseOption.setIdCategoryCatalog(catalog.getId());
                if (response != null) {responseOption.setIdParentResponse(response.getId());}
                responseOption.setIdQuestion(question.getId());

                //initializer

                JOptionPane.showMessageDialog(commentField, "response actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
            
        };
    }

    private ActionListener getSelectedResponse() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    responseToEdit = responseComboBoxMain.getSelectedResponse();

                    commentField.setText(responseToEdit.getCommentResponse());
                    commentField.setEditable(true);

                    optionnField.setText(responseToEdit.getOptionText());
                    optionnField.setEditable(true);

                    // innitializer catalog

                    catalogComboBox.setSelectedCatalog(catalog);
                    catalogComboBox.switcher(true);

                    // initializer de response

                    responseComboBox.setSelectedResponse(response);
                    responseComboBox.switcher(true);

                    // inicializer de question 
                    questionComboBox.setSelectedChapter(question);
                    questionComboBox.switcher(true);
                    
                    updateButton.setEnabled(true);

                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
