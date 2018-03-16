package com.example.demo.studentManage.service.impl;

import com.example.demo.studentManage.mapper.StudentMapper;
import com.example.demo.studentManage.model.Student;
import com.example.demo.studentManage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  @Autowired private StudentMapper studentMapper;

  @Override
  public int addStudent(Student student) {
    return studentMapper.insert(student);
  }

  @Override
  public int deleteStudent(Integer id) {
    return studentMapper.deleteById(id);
  }

  @Override
  public int updateStudent(Student student) {
    return studentMapper.updateById(student);
  }

  @Override
  public Student queryById(Integer id) {
    return studentMapper.queryById(id);
  }

  @Override
  public List<Student> queryAllStudent() {
    return studentMapper.queryAll();
  }
}
