package com.example.demo.studentManage.mapper;

import com.example.demo.studentManage.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

  /**
   * @author TaoZHi
   * @date 2018/3/7 14:48
   * @param [student]
   * @return java.lang.Integer
   */
  Integer insert(Student student);

  /**
   * @author TaoZhi
   * @date 2018/3/7 14:50
   * @param [id]
   * @return int
   */
  int deleteById(Integer id);

  /**
   * @author TaoZhi
   * @date 2018/3/7 14:52
   * @param [student]
   * @return int
   */
  int updateById(Student student);

  /**
   * @author TaoZhi
   * @date 2018/3/7 14:53
   * @param [id]
   * @return com.example.demo.studentManage.model.Student
   */
  Student queryById(Integer id);

  /**
   * @author TaoZhi
   * @date 2018/3/7 15:04
   * @param []
   * @return java.util.List<com.example.demo.studentManage.model.Student>
   */
  List<Student> queryAll();
}
