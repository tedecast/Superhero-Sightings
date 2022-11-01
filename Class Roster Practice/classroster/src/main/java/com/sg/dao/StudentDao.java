/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.dto.Student;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface StudentDao {

    public Student getStudentById(int id);

    public List<Student> getAllStudents();

    public Student addStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudentById(int id);
}
