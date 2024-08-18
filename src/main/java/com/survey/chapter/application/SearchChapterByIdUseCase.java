package com.survey.chapter.application;

import java.util.Optional;

import com.survey.chapter.domain.entity.Chapter;
import com.survey.chapter.domain.service.ChapterService;

public class SearchChapterByIdUseCase {
  private ChapterService chapterService;

  public SearchChapterByIdUseCase(ChapterService chapterService) {
    this.chapterService = chapterService;
  }

  public Optional<Chapter> execute(int id) {
    return this.chapterService.searchById(id);
  }
}
