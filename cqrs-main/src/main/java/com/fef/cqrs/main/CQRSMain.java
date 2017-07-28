package com.fef.cqrs.main;

import com.fef.cqrs.command.CommandServer;
import com.fef.cqrs.query.QueryServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main Application for CQRS Example
 */
public class CQRSMain {

  private static final Logger logger = LogManager.getLogger(CQRSMain.class);

  public static void main(String[] args) {
    CQRSMain cqrsMain = new CQRSMain();
    try {
      cqrsMain.initDB();
      CommandServer commandServer = new CommandServer();
      Thread threadCommandServer = new Thread(commandServer);
      threadCommandServer.start();
      commandServer.startServer();
      QueryServer queryServer = new QueryServer();
      Thread threadQueryServer = new Thread(queryServer);
      threadQueryServer.start();
    } catch (ClassNotFoundException | SQLException e) {
      logger.error(e);
    }
  }

  private void initDB() throws ClassNotFoundException, SQLException {
    Class.forName("org.hsqldb.jdbc.JDBCDriver");
    Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "password");
    connection.setAutoCommit(false);
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet rs;
    String createSQL = readInitDB();
    statement = connection.createStatement();
    statement.execute(createSQL);
    preparedStatement = connection.prepareStatement("SELECT COUNT(*) as total from student");
    rs = preparedStatement.executeQuery();
    while(rs.next()){
      if(rs.getInt(1) == 0){
        preparedStatement = connection.prepareStatement("INSERT INTO student(name) values(?)");
        preparedStatement.setString(1,"fef");
        preparedStatement.executeUpdate();
      }
    }
    connection.commit();
  }

  private String readInitDB() {
    InputStream is = CQRSMain.class.getClassLoader().getResourceAsStream("initdb.sql");
    String sCurrentLine = "";
    try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
      StringBuilder sb = new StringBuilder();
      while ((sCurrentLine = br.readLine()) != null) {
        sb.append(sCurrentLine);
        sb.append("\n");
      }
      sCurrentLine = sb.toString();
    } catch (IOException e) {
      logger.error(e);
    }
    return sCurrentLine;
  }
}
