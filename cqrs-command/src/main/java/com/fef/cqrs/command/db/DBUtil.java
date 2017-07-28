package com.fef.cqrs.command.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility For Database
 */
public class DBUtil {
  private static DBUtil instance = null;
  private static Connection connection= null;
  private DBUtil() {
  }

  public static DBUtil getInstance() {
    if(instance == null){
      instance = new DBUtil();
    }
    return instance;
  }

  public Connection getConnection() throws ClassNotFoundException, SQLException {
    if(connection == null){
      Class.forName("org.hsqldb.jdbc.JDBCDriver");
      connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "password");
    }
    return connection;
  }
}
