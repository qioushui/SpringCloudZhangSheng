package com.zhangsheng.userinfo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhangsheng.userinfo.entity.UserInfo;
import com.zhangsheng.userinfo.service.IUserInfoService;
/* 
 * 此java类是测试连接数据库mysql的联系的
 * zhangsheng
 * 
 * 
 * */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
	@Autowired
	private IUserInfoService iUserInfoService;

	@RequestMapping("/getUserInfo")
	public List<UserInfo> getUserInfo(HttpSession httpSession) {
		//创建session 的时候，添加！（建议用监听去添加，和注销session）
		httpSession.setAttribute("user","zuli");
		List<UserInfo> user = iUserInfoService.getUserInfo();
		System.out.println(user.toString());
		return user;
	}

	@RequestMapping("/addUserInfo")
	public String addUserInfo() {
		UserInfo user = new UserInfo();
		user.setId(3L);
		user.setName("cwh");
		iUserInfoService.insert(user);
		return "success:" + user.toString();
	}

}
