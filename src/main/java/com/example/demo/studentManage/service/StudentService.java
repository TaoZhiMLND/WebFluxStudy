package com.example.demo.studentManage.service;

import com.example.demo.studentManage.model.Student;

import java.util.List;

public interface StudentService {

  int addStudent(Student student);

  int deleteStudent(Integer id);

  int updateStudent(Student student);

  Student queryById(Integer id);

  List<Student> queryAllStudent();
}
