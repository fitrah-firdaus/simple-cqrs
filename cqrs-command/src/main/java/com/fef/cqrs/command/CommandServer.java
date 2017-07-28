package com.fef.cqrs.command;

import com.fef.cqrs.util.ServerMain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;

/**
 * Server that running Command Responsibilities
 */
public class CommandServer implements Runnable {

  private static final Logger logger = LogManager.getLogger(CommandServer.class);

  public void startServer() {


    logger.info("Starting Embedded Jetty Web Container for CommandServer");
    Properties properties = new Properties();
    InputStream is = CommandServer.class.getClassLoader().getResourceAsStream("config-command.properties");
    try {
      properties.load(is);
      ServerMain serverMain = new ServerMain(Long.parseLong(properties.getProperty("server.idleTimeout")),
          Integer.parseInt(properties.getProperty("server.queue")),
          Integer.parseInt(properties.getProperty("server.port")),
          properties.getProperty("server.host"),
          properties.getProperty("server.baseUrl"),
          "com.fef.cqrs.command");
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
