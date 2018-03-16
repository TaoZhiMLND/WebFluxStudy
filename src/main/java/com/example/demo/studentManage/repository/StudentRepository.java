package com.example.demo.studentManage.repository;

import com.example.demo.studentManage.mapper.StudentMapper;
import com.example.demo.studentManage.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StudentRepository {

  @Autowired private StudentMapper studentMapper;

  public Mono<Integer> insert(Student student) {
    return Mono.just(studentMapper.insert(student));
  }

  public Mono<Integer> deleteById(Integer id) {
    return Mono.just(studentMapper.deleteById(id));
  }

  public Mono<Integer> updateById(Student student) {
    return Mono.just(studentMapper.updateById(student));
  }

  public Mono<Student> queryById(Integer id) {
    Mono mono = Mono.justOrEmpty(studentMapper.queryById(id));
    return mono;
  }

  public Flux<Student> queryAll() {
    return Flux.fromIterable(studentMapper.queryAll());
  }
}
