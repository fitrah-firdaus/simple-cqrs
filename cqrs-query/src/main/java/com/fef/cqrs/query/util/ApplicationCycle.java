package com.fef.cqrs.query.util;


import com.fef.cqrs.query.repository.StudentRepository;
import com.fef.cqrs.query.repository.impl.StudentRepositoryImpl;
import com.fef.cqrs.query.service.StudentService;
import com.fef.cqrs.query.service.impl.StudentServiceImpl;

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
