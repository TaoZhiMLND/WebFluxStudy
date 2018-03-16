package com.example.demo.studentManage;

import com.example.demo.studentManage.model.Student;
import com.example.demo.studentManage.repository.StudentParallelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
  private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

  @Autowired private StudentParallelRepository studentRepository;

  @GetMapping("/")
  public String home() {
    return "student manage";
  }

  @GetMapping("/insert")
  public Mono<Integer> insert(Student student) {

    return studentRepository.insert(student);
  }

  public static void main(String[] args) {

    Flux.<String>create(
        sink -> {
          List<String> test = new ArrayList<>();
          test.forEach(t -> sink.next(t));
          sink.complete();
        });

    /*Flux.just("red", "white", "blue")
    			  .log()
    			  .map(String::toUpperCase)
    			  .subscribe(System.out::println);
    */

    /*Mono.delay(Duration.ofMillis(3000))
    .map(d-> "Spring 4")
    .or(Mono.delay(Duration.ofMillis(2000)).map(d -> "spring 5"))
    .then(Mono.just("world"))
    .log()
    .elapsed()
    .subscribe();*/

    /*Flux.just("red", "white", "blue", "aaa", "bbb", "ccc", "ddd", "eee", "fff", "ggg", "hhh")
    .log()
    .flatMap(value -> Mono.just(value.toUpperCase()).subscribeOn(Schedulers.parallel()), 2)
    .subscribe(
        value -> {
          logger.info("Consumed: " + value);
          logger.info(
              "Thread: "
                  + Thread.currentThread().getId()
                  + ","
                  + Thread.currentThread().getName());
        });*/

    /*Flux.just("red", "white", "blue")
    .log()
    .map(String::toUpperCase)
    .subscribeOn(Schedulers.parallel())
    .subscribe();*/

    Flux.just("red", "white", "blue")
        .log()
        .map(String::toUpperCase)
        .subscribeOn(Schedulers.newParallel("sub"))
        .publishOn(Schedulers.newParallel("pub"), 2)
        .subscribe(
            value -> {
              logger.info("Consumed: " + value);
            });

    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
