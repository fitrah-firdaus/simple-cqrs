package com.fef.cqrs.command.servlet;

import com.fef.cqrs.command.model.Student;
import com.fef.cqrs.command.util.ApplicationCycle;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Servlet For Command Responsibilities
 */
@Path("command")
public class CommandServlet {


  @POST
  @Path("insert")
  @Produces(MediaType.APPLICATION_JSON)
  public Response insertStudent(Student student) {
    int result = ApplicationCycle.getInstance().getStudentService().insertStudent(student.getName());
    Student studentOutput = new Student();
    studentOutput.setName(student.getName());
    return Response.status(200).entity(studentOutput).build();
  }

  @POST
  @Path("update")
  @Produces(MediaType.APPLICATION_JSON)
  public String updateStudent(Student student) {
    int result = ApplicationCycle.getInstance().getStudentService().updateStudent(student.getId(), student.getName());
    return result + " row updated";
  }

  @POST
  @Path("delete")
  @Produces(MediaType.APPLICATION_JSON)
  public String deleteStudent(Student student) {
    int result = ApplicationCycle.getInstance().getStudentService().deleteStudent(student.getId());
    return result + " row inserted";
  }
}
