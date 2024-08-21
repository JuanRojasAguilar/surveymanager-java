package com.survey.subresponseOption.domain.service;

import java.util.List;
import java.util.Optional;

import com.survey.subresponseOption.domain.entity.SubresponseOption;

public interface SubresponseOptionService {
  void add(SubresponseOption SubresponseOption);

  Optional<SubresponseOption> searchById(int id);

  Optional<List<SubresponseOption>> showAll();

  void update(SubresponseOption SubresponseOption);

  boolean delete(int id);
}
