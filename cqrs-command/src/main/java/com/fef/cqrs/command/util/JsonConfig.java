package com.fef.cqrs.command.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonConfig implements ContextResolver<ObjectMapper> {

  private final ObjectMapper mapper;

  public JsonConfig() {
    mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }


  @Override
  public ObjectMapper getContext(Class<?> type) {
    return mapper;
  }
}
