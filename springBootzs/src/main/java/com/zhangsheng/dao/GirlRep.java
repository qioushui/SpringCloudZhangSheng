package com.zhangsheng.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhangsheng.entity.Girl;

public interface GirlRep extends JpaRepository<Girl,Integer>{
	
}
