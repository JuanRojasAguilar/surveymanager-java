package com.survey.question.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.question.domain.entity.Question;

public interface QuestionService {
  void add(Question question);

  Optional<Question> searchById(int id);

  Optional<List<Question>> showAll(int limit, int offset);

  void update(Question question);

  boolean delete(int id);
}
