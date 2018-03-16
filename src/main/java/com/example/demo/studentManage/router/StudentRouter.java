package com.example.demo.studentManage.router;

import com.example.demo.studentManage.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StudentRouter {

  private StudentHandler studentHandler;

  public StudentRouter(StudentHandler studentHandler) {
    this.studentHandler = studentHandler;
  }

  @Bean
  public RouterFunction<?> routerFunction() {

    return route(
            GET("/student/queryById/{id}").and(accept(MediaType.APPLICATION_JSON)),
            request -> studentHandler.handleQueryById(Integer.valueOf(request.pathVariable("id"))))
        .and(
            route(
                GET("/student/queryAll").and(accept(MediaType.APPLICATION_JSON)),
                studentHandler::handleQueryAll))
        .and(
            route(
                GET("/student/deleteById/{id}").and(accept(MediaType.APPLICATION_JSON)),
					request -> studentHandler.handleDeleteById(Integer.valueOf(request.pathVariable("id"))))
        .and(
            route(
                GET("/student/functionalInsert").and(accept(MediaType.APPLICATION_JSON)),
                studentHandler::handleInsert))
        .and(
            route(
                GET("/student/functionalUpdateById").and(accept(MediaType.APPLICATION_JSON)),
                studentHandler::handleUpdateById)));
  }
}
