/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.dto.Teacher;
import java.util.List;

/**
 *
 * @author Teresa
 */
public interface TeacherDao {

    public Teacher getTeacherById(int id);

    public List<Teacher> getAllTeachers();

    public Teacher addTeacher(Teacher teacher);

    public void updateTeacher(Teacher teacher);

    public void deleteTeacherById(int id);
}
