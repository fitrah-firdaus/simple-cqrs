package com.fef.cqrs.query.service.impl;

import com.fef.cqrs.query.model.Student;
import com.fef.cqrs.query.service.StudentService;
import com.fef.cqrs.query.util.ApplicationCycle;

import java.util.List;
public class StudentServiceImpl implements StudentService {
  @Override
  public List<Student> findAll() {
    return ApplicationCycle.getInstance().getStudentRepository().findAll();
  }

  @Override
  public List<Student> findByName(String name) {
    return ApplicationCycle.getInstance().getStudentRepository().findByName(name);
  }
}
