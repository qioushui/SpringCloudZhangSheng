package com.zhangsheng.userinfo.service;

import java.util.List;

import com.zhangsheng.userinfo.entity.UserInfo;

public interface IUserInfoService {
	public List<UserInfo> getUserInfo();
    
    public void insert(UserInfo user);
}
