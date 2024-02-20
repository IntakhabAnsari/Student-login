package com.rahi.in.service;

import java.util.List;
 
import com.rahi.in.entity.UserInfo;

public interface UserInfoService {

	public List<UserInfo> getAllUserInfo();
	public UserInfo getUserInfoById(Integer id);
	public void updateUserInfo(Integer id, UserInfo updatedUserInfo);
	public void deleteUserInfo(Integer id);
}