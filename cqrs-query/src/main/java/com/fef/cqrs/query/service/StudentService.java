package com.fef.cqrs.query.service;

import com.fef.cqrs.query.model.Student;

import java.util.List;

public interface StudentService {
  List<Student> findAll();
  List<Student> findByName(String name);
}
