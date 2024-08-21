package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.catalog.domain.entity.Catalog;
import com.survey.chapter.domain.entity.Chapter;

public class ChapterComboBox extends JPanel {
    private JComboBox<Chapter> chapterComboBox;

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
        List<Chapter> chapters = //InitializerChapter 
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
