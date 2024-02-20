package com.rahi.in.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahi.in.entity.UserInfo;
import com.rahi.in.exception.ResourceNotFoundException;
import com.rahi.in.repository.UserInfoRepository;
import com.rahi.in.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoRepository repository;
	
	@Override
	public List<UserInfo> getAllUserInfo() {
		
		return repository.findAll();
	}

	@Override
	public UserInfo getUserInfoById(Integer id) {
		UserInfo userInfo = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("UserInfo", "id", id ));
		return userInfo;
	}

	@Override
	public void updateUserInfo(Integer id, UserInfo updatedUserInfo) {
		UserInfo userInfo = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("UserInfo", "id", id));
		
		userInfo.setName(updatedUserInfo.getName());
		userInfo.setEmail(updatedUserInfo.getEmail());
		
		repository.save(userInfo);
		
	}

	@Override
	public void deleteUserInfo(Integer id) {
		UserInfo deleteUserInfo = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("UserInfo", "id", id));
		repository.delete(deleteUserInfo);
	}

	
}