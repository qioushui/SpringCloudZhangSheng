package com.zhangsheng.userinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangsheng.userinfo.dao.IUserInfoMapper;
import com.zhangsheng.userinfo.entity.UserInfo;
import com.zhangsheng.userinfo.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService{

	
	@Autowired
    private IUserInfoMapper iUserInfoMapper;

	@Override
	public List<UserInfo> getUserInfo() {
		// TODO Auto-generated method stub
		return iUserInfoMapper.findUserInfo();
	}

	@Override
	public void insert(UserInfo user) {
		// TODO Auto-generated method stub
		iUserInfoMapper.addUserInfo(user);
	}
	
}
