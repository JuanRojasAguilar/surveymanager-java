package com.survey.chapter.application;

import com.survey.chapter.domain.service.ChapterService;

public class DeleteChapterUseCase {
  private ChapterService chapterService;

  public DeleteChapterUseCase(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  public boolean execute(int id) {
    return this.chapterService.delete(id);
  }
}
