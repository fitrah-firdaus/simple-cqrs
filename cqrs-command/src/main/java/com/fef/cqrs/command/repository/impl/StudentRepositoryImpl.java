package com.fef.cqrs.command.repository.impl;

import com.fef.cqrs.command.db.DBUtil;
import com.fef.cqrs.command.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentRepositoryImpl implements StudentRepository {
  private static Logger logger = LogManager.getLogger(StudentRepositoryImpl.class);

  @Override
  public int insertStudent(String name) {
    try {
      PreparedStatement preparedStatement = DBUtil.getInstance().getConnection()
          .prepareStatement("INSERT INTO student(name) values(?)");
      preparedStatement.setString(1,name);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
      logger.error(e);
    }
    return 0;
  }

  @Override
  public int updateStudent(long id, String name) {
    try {
      PreparedStatement preparedStatement = DBUtil.getInstance().getConnection()
          .prepareStatement("UPDATE student SET name = ? WHERE id = ?");
      preparedStatement.setString(1,name);
      preparedStatement.setLong(2, id);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
      logger.error(e);
    }
    return 0;
  }

  @Override
  public int deleteStudent(long id) {
    try {
      PreparedStatement preparedStatement = DBUtil.getInstance().getConnection()
          .prepareStatement("DELETE FROM student WHERE id = ?");
      preparedStatement.setLong(1, id);
      return preparedStatement.executeUpdate();
    } catch (ClassNotFoundException | SQLException e) {
      logger.error(e);
    }
    return 0;
  }
}
