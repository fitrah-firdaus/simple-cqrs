package com.fef.cqrs.command.util;

import com.fef.cqrs.command.repository.impl.StudentRepositoryImpl;
import com.fef.cqrs.command.service.impl.StudentServiceImpl;
import com.fef.cqrs.command.repository.StudentRepository;
import com.fef.cqrs.command.service.StudentService;

/**
 *
 */
public class ApplicationCycle {
  private static ApplicationCycle instance;
  private final StudentRepository studentRepository;
  private final StudentService studentService;

  private ApplicationCycle() {
    studentRepository = new StudentRepositoryImpl();
    studentService = new StudentServiceImpl();
  }

  public static ApplicationCycle getInstance() {
    if(instance == null){
      instance = new ApplicationCycle();
    }
    return instance;
  }

  public StudentRepository getStudentRepository() {
    return studentRepository;
  }

  public StudentService getStudentService() {
    return studentService;
  }
}
