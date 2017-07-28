package com.fef.cqrs.query.repository;

import com.fef.cqrs.query.model.Student;

import java.util.List;

public interface StudentRepository {
  List<Student> findAll();
  List<Student> findByName(String name);
}
