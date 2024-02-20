package com.rahi.in.controller;

import com.rahi.in.dto.AuthRequest;
import com.rahi.in.dto.AuthResponse;
import com.rahi.in.entity.Student;
import com.rahi.in.entity.UserInfo;
import com.rahi.in.service.StudentService;
import com.rahi.in.serviceImpl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Void> saveStudent(@RequestBody Student student){
		studentService.createStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/getList")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Student>> getAllStudentsList(){
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Student> getStudentById(@PathVariable Integer id ){
		return ResponseEntity.ok(studentService.getStudentById(id));
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Void> updateStudent(@PathVariable Integer id, @RequestBody Student student){
		studentService.updateStudent(id, student);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id){
		studentService.deleteStudent(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not scure";
	}
	
	@PostMapping("/new")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return studentService.addUser(userInfo);	
	}
	@PostMapping("/authenticate")
	public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	AuthResponse ar = new AuthResponse();
            String token = jwtService.generateToken(authRequest.getUsername());
            UserInfo ui = studentService.getStudentByEmail(authRequest.getUsername());
            ar.setId(ui.getId());
            ar.setName(ui.getName());
        	ar.setToken(token);
        	return ar;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
	}
}