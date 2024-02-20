package com.rahi.in.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rahi.in.dto.UserRequest;
import com.rahi.in.entity.Student;
import com.rahi.in.entity.UserInfo;
import com.rahi.in.exception.ResourceNotFoundException;
import com.rahi.in.repository.StudentRepository;
import com.rahi.in.repository.UserInfoRepository;
import com.rahi.in.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createStudent(Student student) {
		studentRepository.save(student);

	}

	@Override
	public List<Student> getAllStudents() {

		return studentRepository.findAll();
	}

	@Override
	public Student getStudentById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		return student;
	}

	@Override
	public void updateStudent(Integer id, Student updatedStudent) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

		student.setName(updatedStudent.getName());
		student.setEmail(updatedStudent.getEmail());
		student.setPhoneNo(updatedStudent.getPhoneNo());

		studentRepository.save(student);
	}

	public void updateUser(Integer id, UserRequest request) {
		UserInfo ui = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

		ui.setName(request.getName());

		repository.save(ui);
	}

	@Override
	public void deleteStudent(Integer id) {
		Student deleteStudent = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
		studentRepository.delete(deleteStudent);
	}

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to System";
	}

	@Override
	public UserInfo getStudentByEmail(String email) {
		UserInfo ui = repository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "email", email));
		return ui;
	}
}