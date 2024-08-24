package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class ChapterComboBox extends JPanel {
    private JComboBox<Chapter> chapterComboBox;
    private ShowAllChaptersUseCase showAllChaptersUseCase;
    private ChapterService chapterService;

    public ChapterComboBox() {
        chapterComboBox = new JComboBox<>();

        setLayout(new BorderLayout());

        add(chapterComboBox, BorderLayout.CENTER);
    }

    public ChapterComboBox(ActionListener actionListenerComboBox) {
        chapterComboBox = new JComboBox<>();
        chapterComboBox.addActionListener(actionListenerComboBox);

        setLayout(new BorderLayout());

        add(chapterComboBox, BorderLayout.CENTER);
    }

    public void updateChapters() {
        showAllChaptersUseCase = new ShowAllChaptersUseCase(chapterService);
        List<Chapter> chapters = showAllChaptersUseCase.execute(10, 0).get();
        chapterComboBox.removeAllItems();
        chapters.forEach(chapter -> {
            chapterComboBox.addItem(chapter);
        });

        if (chapterComboBox.getItemCount() == 0) {
            chapterComboBox.setEditable(false);
        }
    }

    public void switcher(boolean swich) {
        chapterComboBox.setEditable(swich);
    }

    public void setSelectedChapter(Chapter chapter) {
        chapterComboBox.addItem(chapter);
        chapterComboBox.setSelectedItem(chapter);
    }

    public Chapter getSelectedChapter() {
        return (Chapter) chapterComboBox.getSelectedItem();
    }
}
