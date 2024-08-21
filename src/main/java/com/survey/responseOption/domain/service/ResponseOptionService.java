package com.survey.responseOption.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.responseOption.domain.entity.ResponseOption;

public interface ResponseOptionService {
  void add(ResponseOption responseOption);

  Optional<ResponseOption> searchById(int id);

  Optional<List<ResponseOption>> showAll();

  void update(ResponseOption responseOption);

  boolean delete(int id);
}
