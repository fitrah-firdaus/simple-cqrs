package com.fef.cqrs.command.repository;

/**
 * Repository For Student
 */
public interface StudentRepository {
  int insertStudent(String name);
  int updateStudent(long id,String name);
  int deleteStudent(long id);
}
