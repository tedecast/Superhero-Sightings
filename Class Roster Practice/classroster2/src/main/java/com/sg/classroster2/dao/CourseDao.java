/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster2.dao;

import com.sg.classroster2.entities.Course;
import com.sg.classroster2.entities.Student;
import com.sg.classroster2.entities.Teacher;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface CourseDao {

    Course getCourseById(int id);

    List<Course> getAllCourses();

    Course addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourseById(int id);

    List<Course> getCoursesForTeacher(Teacher teacher);

    List<Course> getCoursesForStudent(Student student);
}
