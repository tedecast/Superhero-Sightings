/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.dto.Course;
import com.sg.dto.Student;
import com.sg.dto.Teacher;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface CourseDao {

    public Course getCourseById(int id);

    public List<Course> getAllCourses();

    public Course addCourse(Course course);

    public void updateCourse(Course course);

    public void deleteCourseById(int id);

    public List<Course> getCoursesForTeacher(Teacher teacher);

    public List<Course> getCoursesForStudent(Student student);
}
