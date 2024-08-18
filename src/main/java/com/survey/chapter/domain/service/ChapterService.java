package com.survey.chapter.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.chapter.domain.entity.Chapter;

public interface ChapterService {
  void add(Chapter chapter);

  Optional<Chapter> searchById(int id);

  Optional<List<Chapter>> showAll(int limit, int offset);

  void update(Chapter chapter);

  boolean delete(int id);
}
