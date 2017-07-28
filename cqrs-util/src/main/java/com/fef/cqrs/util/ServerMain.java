package com.fef.cqrs.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ServerMain {
  private static final Logger logger = LogManager.getLogger(ServerMain.class);
  private long idleTimeout;
  private int queue;
  private int port;
  private String host;
  private String baseUrl;
  private String packages;

  public ServerMain(long idleTimeout, int queue, int port, String host, String baseUrl, String packages) {
    this.idleTimeout = idleTimeout;
    this.queue = queue;
    this.port = port;
    this.host = host;
    this.baseUrl = baseUrl;
    this.packages = packages;
  }

  public ServerMain() {
  }

  public long getIdleTimeout() {
    return idleTimeout;
  }

  public void setIdleTimeout(long idleTimeout) {
    this.idleTimeout = idleTimeout;
  }

  public int getQueue() {
    return queue;
  }

  public void setQueue(int queue) {
    this.queue = queue;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String getPackages() {
    return packages;
  }

  public void setPackages(String packages) {
    this.packages = packages;
  }

  public void startServer() {
    Server server = new Server();
    ServerConnector serverConnector = new ServerConnector(server);
    serverConnector.setIdleTimeout(idleTimeout);
    serverConnector.setAcceptQueueSize(queue);
    serverConnector.setPort(port);
    serverConnector.setHost(host);
    ServletContextHandler contextHandler = new ServletContextHandler(server, baseUrl, true, false);
    ResourceConfig config = new ResourceConfig();
    config.packages(packages);
    config.register(JacksonFeature.class);
    ServletHolder servletHolder = new ServletHolder(new ServletContainer(config));
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "org.jersey.media.type");
    contextHandler.addServlet(servletHolder, baseUrl);
    server.addConnector(serverConnector);
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      logger.error(e);
    }
  }

  @Override
  public String toString() {
    return "ServerMain{" +
        "idleTimeout=" + idleTimeout +
        ", queue=" + queue +
        ", port=" + port +
        ", host='" + host + '\'' +
        ", baseUrl='" + baseUrl + '\'' +
        ", packages='" + packages + '\'' +
        '}';
  }
}
