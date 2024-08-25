package com.survey.chapter.application;

import java.util.List;
import java.util.Optional;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class ShowAllChaptersUseCase {
  private ChapterService chapterService;

  public ShowAllChaptersUseCase(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  public Optional<List<Chapter>> execute() {
    return this.chapterService.showAll();
  }
}
