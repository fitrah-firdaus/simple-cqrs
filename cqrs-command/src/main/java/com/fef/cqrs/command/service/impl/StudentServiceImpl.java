package com.fef.cqrs.command.service.impl;

import com.fef.cqrs.command.service.StudentService;
import com.fef.cqrs.command.util.ApplicationCycle;

public class StudentServiceImpl implements StudentService {
  @Override
  public int insertStudent(String name) {
    return ApplicationCycle.getInstance().getStudentRepository().insertStudent(name);
  }

  @Override
  public int updateStudent(long id, String name) {
    return 0;  // TODO impl
  }

  @Override
  public int deleteStudent(long id) {
    return 0;  // TODO impl
  }
}
