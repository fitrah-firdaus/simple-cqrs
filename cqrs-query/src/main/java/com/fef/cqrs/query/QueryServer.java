package com.fef.cqrs.query;

import com.fef.cqrs.util.ServerMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

public class QueryServer implements Runnable {
  private static final Logger logger = LogManager.getLogger(QueryServer.class);

  public void startServer() {
    logger.info("Starting Embedded Jetty Web Container for QueryServer");
    Properties properties = new Properties();
    InputStream is = QueryServer.class.getClassLoader().getResourceAsStream("config-query.properties");
    try {
      properties.load(is);
      ServerMain serverMain = new ServerMain(Long.parseLong(properties.getProperty("server.idleTimeout")),
          Integer.parseInt(properties.getProperty("server.queue")),
          Integer.parseInt(properties.getProperty("server.port")),
          properties.getProperty("server.host"),
          properties.getProperty("server.baseUrl"),
          "com.fef.cqrs.query");
      serverMain.startServer();
    } catch (Exception e) {
      logger.error(e);
    }
  }

  @Override
  public void run() {
    this.startServer();
  }
}
