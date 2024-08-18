package com.survey.chapter.application;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class UpdateChapterUseCase {
  private ChapterService chapterService;

  public UpdateChapterUseCase(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  public void execute(Chapter chapter) {
    this.chapterService.update(chapter);
  }
}
