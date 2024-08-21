package com.survey.question.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.infraestructure.ui.ChapterComboBox;
import com.survey.question.domain.entity.Question;
import com.survey.ui.StyleDefiner;

public class UpdateChapteQuestionJFrame extends JFrame {
    //initializer de question
    private JButton returnButton;

    private QuestionComboBox questionComboBox;
    private JComboBox<String> responseType;
    private ChapterComboBox chapterComboBox;
    private JTextField commentField;
    private JTextField questionField;
    private JButton updateButton;

    private boolean initializer;
    private Question questionToEdit;

    public UpdateChapteQuestionJFrame() {
        initializer = true;

        initComponents();
        createUpdateFrame();

        initializer = false;
    }

    private void initComponents() {
        questionComboBox = new QuestionComboBox(getSelectedQuestion());
        chapterComboBox = new ChapterComboBox();
        responseType = new JComboBox<>();
        responseType.addItem("seleccion");
        responseType.addItem("escrita");
    }

    private void createUpdateFrame() {
        setTitle("Update question");
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
        JLabel comboBoxLabel = new JLabel("question");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        questionComboBox.updateQuestions();
        formPanel.add(questionComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel comboBoxLabelChapter = new JLabel("Chapter");
        formPanel.add(comboBoxLabelChapter, gbc);   

        gbc.gridx = 1;
        chapterComboBox.updateChapters();
        chapterComboBox.switcher(false);
        formPanel.add(chapterComboBox, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel typeLabel = new JLabel("tipo: ");
        formPanel.add(typeLabel, gbc);

        gbc.gridx = 1;
        responseType.setEditable(false);
        formPanel.add(responseType, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel commentLabel = new JLabel("comentario: ");
        formPanel.add(commentLabel, gbc);

        gbc.gridx = 1;
        commentField = StyleDefiner.defineFieldStyle(commentField);
        commentField.setEditable(false);
        formPanel.add(commentField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel questionLabel = new JLabel("pregunta: ");
        formPanel.add(questionLabel, gbc);

        gbc.gridx = 1;
        questionField = StyleDefiner.defineFieldStyle(questionField);
        questionField.setEditable(false);
        formPanel.add(questionField, gbc);

        row++;
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        updateButton = StyleDefiner.defineButtonStyle(new JButton("actualizar"));
        updateButton.setEnabled(false);
        updateButton.addActionListener(actualizarQuestion());
        formPanel.add(updateButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private ActionListener actualizarQuestion() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String comment = commentField.getText();
                String questionText = questionField.getText();
                String type = (String) responseType.getSelectedItem();
                Chapter chapter = chapterComboBox.getSelectedChapter();

                if (questionText.isEmpty()) {
                    JOptionPane.showMessageDialog(questionField, "campos incompletos", "error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                commentField.setText("");
                questionField.setText("");

                // initializer trae todos los questions

                List<Question> numeroDeQuestions = questions.stream()
                                                            .filter(question.getIdChapter == chapter.getId())
                                                            .toArray();

                Question question = new Question();
                questionToEdit.setQuestion(questionText);
                questionToEdit.setQuestionNumber(String.valueOf(numeroDeQuestions.size() + 1));
                questionToEdit.setIdChapter(chapter.getId());
                questionToEdit.setType(type);
                questionToEdit.setComment(comment);

                //initializer

                JOptionPane.showMessageDialog(questionField, "question actualizado", "accion completada", JOptionPane.WARNING_MESSAGE);
            }
        };
    }

    private ActionListener getSelectedQuestion() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!initializer) {
                    questionToEdit = questionComboBox.getSelectedQuestion();
                    // logica para obtener el survey de este chapter
                    commentField.setText(questionToEdit.getQuestionComment());
                    commentField.setEditable(true);

                    questionField.setText(questionToEdit.getQuestion());
                    questionField.setEditable(true);

                    responseType.setSelectedItem(questionToEdit.getType());
                    responseType.setEditable(true);

                    // inicializer de chapter 
                    chapterComboBox.setSelectedChapter(chapter);
                    chapterComboBox.switcher(true);
                    
                    updateButton.setEnabled(true);

                }
            }
        };
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
