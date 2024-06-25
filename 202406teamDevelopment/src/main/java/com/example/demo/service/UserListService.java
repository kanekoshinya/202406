package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserListEntity;
import com.example.demo.repository.UserListRepository;

@Service
public class UserListService {

	// ユーザー一覧情報
	@Autowired
	private UserListRepository userlistRepository;

	// 全検索
	// 検索結果
	public List<UserListEntity> searchAll() {
		return userlistRepository.findAll();
	}
}