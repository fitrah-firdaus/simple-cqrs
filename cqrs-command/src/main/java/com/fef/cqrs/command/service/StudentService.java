package com.fef.cqrs.command.service;

public interface StudentService {
  int insertStudent(String name);
  int updateStudent(long id,String name);
  int deleteStudent(long id);
}
