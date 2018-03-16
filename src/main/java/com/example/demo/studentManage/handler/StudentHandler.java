package com.example.demo.studentManage.handler;

import com.example.demo.studentManage.mapper.StudentMapper;
import com.example.demo.studentManage.model.Student;
import com.example.demo.studentManage.repository.StudentParallelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author TaoZhi
 * @date 2018/3/7 14:54
 */
@Service
public class StudentHandler {
  @Autowired private StudentParallelRepository studentRepository;

  @Autowired private StudentMapper studentMapper;

  public Mono<ServerResponse> handleQueryAll(ServerRequest request) {
    return ServerResponse.ok()
        .body(studentRepository.queryAll(), Student.class)
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  /*  public Mono<ServerResponse> handleQueryById(int id) {
    System.out.println("1");
    Mono result =
        Mono.just(id)
            .flatMap(
                value ->
                    Mono.justOrEmpty(studentMapper.queryById(id))
                        .subscribeOn(Schedulers.parallel()));
    System.out.println("2");
    return ServerResponse.ok().body(result, Student.class);
  }*/

  public Mono<ServerResponse> handleQueryById(int id) {
    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    Mono<ServerResponse> responseMono =
        studentRepository
            .queryById(id)
			.log()
            .flatMap(student -> ServerResponse.ok().body(Mono.just(student), Student.class))
            .subscribeOn(Schedulers.parallel());
    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    return responseMono;
  }

  public Mono<ServerResponse> handleDeleteById(int id) {
    return ServerResponse.ok().body(studentRepository.deleteById(id), Integer.class);
  }

  public Mono<ServerResponse> handleDeleteById(ServerRequest request) {
    return ServerResponse.ok()
        .body(
            studentRepository.deleteById(Integer.valueOf(request.pathVariable("id"))),
            Integer.class);
  }

  public Mono<ServerResponse> handleInsert(ServerRequest request) {
    Student student = getStudentFromRequest(request);
    return ServerResponse.ok().body(studentRepository.insert(student), Integer.class);
  }

  public Mono<ServerResponse> handleUpdateById(ServerRequest request) {
    Student student = getStudentFromRequest(request);
    return ServerResponse.ok().body(studentRepository.updateById(student), Integer.class);
  }

  private Student getStudentFromRequest(ServerRequest request) {
    Student student = new Student();
    student.setId(Integer.valueOf(request.queryParam("id").get()));
    student.setName(request.queryParam("name").get());
    student.setAge(Integer.valueOf(request.queryParam("age").get()));
    return student;
  }
}
