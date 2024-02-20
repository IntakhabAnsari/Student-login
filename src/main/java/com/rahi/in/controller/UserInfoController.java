package com.rahi.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahi.in.entity.UserInfo;
import com.rahi.in.serviceImpl.UserInfoServiceImpl;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private UserInfoServiceImpl userService;
	
	@GetMapping("/getList")
	public ResponseEntity<List<UserInfo>> getAllUserInfoList(){
		return ResponseEntity.ok(userService.getAllUserInfo());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserInfo> getUserInfoById(@PathVariable Integer id){
		return ResponseEntity.ok(userService.getUserInfoById(id));
	}
	
}