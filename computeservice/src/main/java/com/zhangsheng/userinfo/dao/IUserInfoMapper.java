package com.zhangsheng.userinfo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.zhangsheng.userinfo.entity.UserInfo;


@Mapper
public interface IUserInfoMapper {
	public List<UserInfo> findUserInfo();
	public int addUserInfo(UserInfo user);
	public int delUserInfo(int id);
}
