package com.rahi.in.service;

import java.util.List;
import java.util.Optional;

import com.rahi.in.entity.Student;
import com.rahi.in.entity.UserInfo;

public interface StudentService {

	public void createStudent(Student student);
	public List<Student> getAllStudents();
	public Student getStudentById(Integer id);
	public void updateStudent(Integer id, Student updatedStudent);
	public void deleteStudent(Integer id);
	public String addUser(UserInfo userInfo);
	public UserInfo getStudentByEmail(String email);
	
}