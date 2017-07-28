package com.fef.cqrs.query.servlet;

import com.fef.cqrs.query.util.ApplicationCycle;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("query")
public class QueryServlet {

  @GET()
  @Path("findAll")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAll(){
    return Response.status(200).entity(ApplicationCycle.getInstance().getStudentService().findAll()).build();
  }

  @GET()
  @Path("findByName")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getByName(@QueryParam("name") String name){
    return Response.status(200).entity(ApplicationCycle.getInstance().getStudentService().findByName(name)).build();
  }
}
