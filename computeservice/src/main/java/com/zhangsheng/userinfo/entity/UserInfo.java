package com.zhangsheng.userinfo.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

}
