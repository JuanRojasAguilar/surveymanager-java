package com.survey.chapter.infraestructure.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.survey.chapter.application.ShowAllChaptersUseCase;
import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;
import com.survey.chapter.infraestructure.repository.ChapterRepository;

public class ChapterComboBox extends JPanel {
    private JComboBox<Chapter> chapterComboBox;
    private ShowAllChaptersUseCase showAllChaptersUseCase;
    private ChapterService chapterService = new ChapterRepository();

    public ChapterComboBox() {
        chapterComboBox = new JComboBox<>();
        chapterComboBox.setPreferredSize(new Dimension(120, 30));

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
            chapterComboBox.setEnabled(false);
            revalidate();
            repaint();
        }
    }

    public void switcher(boolean swich) {
        chapterComboBox.setEnabled(swich);
    }

    public void setSelectedChapter(Chapter chapter) {
        chapterComboBox.addItem(chapter);
        chapterComboBox.setSelectedItem(chapter);
    }

    public Chapter getSelectedChapter() {
        return (Chapter) chapterComboBox.getSelectedItem();
    }
}
