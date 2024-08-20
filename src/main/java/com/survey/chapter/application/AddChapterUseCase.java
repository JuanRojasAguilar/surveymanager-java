package com.survey.chapter.application;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class AddChapterUseCase {
  private ChapterService chapterService;

  public AddChapterUseCase(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  public void execute(Chapter chapter) {
    this.chapterService.add(chapter);
  }
}
