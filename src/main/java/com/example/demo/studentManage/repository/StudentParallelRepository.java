package com.example.demo.studentManage.repository;

import com.example.demo.studentManage.mapper.StudentMapper;
import com.example.demo.studentManage.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Repository
public class StudentParallelRepository {

  @Autowired private StudentMapper studentMapper;

  public Mono<Integer> insert(Student student) {
    return Mono.just(studentMapper.insert(student));
  }

  public Mono<Integer> deleteById(Integer id) {
    /*Mono.just(studentMapper.deleteById(id));*/
    return Mono.just(id)
        .flatMap(
            value -> Mono.just(studentMapper.deleteById(id)).subscribeOn(Schedulers.parallel()));
  }

  public Mono<Integer> updateById(Student student) {
    return Mono.just(studentMapper.updateById(student));
  }

  public Mono<Student> queryById(Integer id) {
    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    Mono<Student> studentMono =
        Mono.just(id)
            .flatMap(
                value ->
                    Mono.justOrEmpty(studentMapper.queryById(id))
                        .log()
                        .subscribeOn(Schedulers.parallel()));



	  Mono.fromCallable(() -> queryById(id))
			  .subscribeOn(Schedulers.parallel());



    System.out.println(Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
    return studentMono;
  }

  public Flux<Student> queryAll() {
    return Flux.fromIterable(studentMapper.queryAll()).log().subscribeOn(Schedulers.parallel());
  }
}
