package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.survey.chapter.application.DeleteChapterUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.repository.ChapterRepository;
import com.survey.ui.StyleDefiner;

public class DeleteChapterJFrame extends JFrame{
    private ChapterComboBox chapterComboBox;
    private JButton returnButton;

    private ChapterService chapterService = new ChapterRepository();
    private DeleteChapterUseCase deleteChapterUseCase;

    public DeleteChapterJFrame() {
        initComponents();

        createDeleteChapter();
    }

    private void initComponents() {
        chapterComboBox = new ChapterComboBox();
    }

    private void createDeleteChapter() {
        setTitle("Delete Chapter");
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
        JLabel comboBoxLabel = new JLabel("Chapter");
        formPanel.add(comboBoxLabel, gbc);

        gbc.gridx = 1;
        chapterComboBox.updateChapters();
        formPanel.add(chapterComboBox, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = StyleDefiner.defineButtonStyle(new JButton("Borrar"));
        deleteButton.addActionListener(e -> {
            Chapter chapter = chapterComboBox.getSelectedChapter();
            
            int continuar = JOptionPane.showConfirmDialog(chapterComboBox, "seguro que quieres eliminar a este chapter?", "¿?", JOptionPane.YES_NO_OPTION);
            
            if (continuar == 0) {
                deleteChapterUseCase = new DeleteChapterUseCase(chapterService);
                boolean hasBeenDeleted = deleteChapterUseCase.execute(chapter.getId());
                String mensaje = hasBeenDeleted ? "eliminado correctamente" : "No hemos podido eliminarlo, intenta de nuevo";
                JOptionPane.showMessageDialog(null, mensaje, "eliminado", JOptionPane.INFORMATION_MESSAGE);
                chapterComboBox.updateChapters();
            }
        });
        formPanel.add(deleteButton, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    public void setReturnActionListener(ActionListener actionListener) {
        returnButton.addActionListener(actionListener);
    }
}
