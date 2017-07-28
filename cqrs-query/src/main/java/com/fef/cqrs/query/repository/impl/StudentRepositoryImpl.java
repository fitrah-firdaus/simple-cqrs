package com.fef.cqrs.query.repository.impl;

import com.fef.cqrs.query.db.DBUtil;
import com.fef.cqrs.query.model.Student;
import com.fef.cqrs.query.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
  private static final Logger logger = LogManager.getLogger(StudentRepositoryImpl.class);
  @Override
  public List<Student> findAll() {
    List<Student> studentList = new LinkedList<>();
    try {
      PreparedStatement preparedStatement = DBUtil.getInstance().getConnection()
        .prepareStatement("SELECT * FROM Student");
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next()){
        Student student = new Student();
        student.setId(rs.getLong(1));
        student.setName(rs.getString(2));
        studentList.add(student);
      }
    } catch (SQLException | ClassNotFoundException e) {
      logger.error(e);
    }
    return studentList;
  }

  @Override
  public List<Student> findByName(String name) {
    List<Student> studentList = new LinkedList<>();
    try {
      PreparedStatement preparedStatement = DBUtil.getInstance().getConnection()
          .prepareStatement("SELECT * FROM Student WHERE name = ?");
      preparedStatement.setString(1,name);
      ResultSet rs = preparedStatement.executeQuery();
      while(rs.next()){
        Student student = new Student();
        student.setId(rs.getLong(1));
        student.setName(rs.getString(2));
        studentList.add(student);
      }
    } catch (SQLException | ClassNotFoundException e) {
      logger.error(e);
    }
    return studentList;
  }
}
