package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserListEntity;
import com.example.demo.repository.UserListRepository;
//ユーザー一覧情報Service
@Service
public class UserListService {

	// ユーザー一覧情報Repository
	@Autowired
	private UserListRepository userlistRepository;

	// 全ユーザー検索メソッド
	// 検索結果
	public List<UserListEntity> getAllUsers() {
		return userlistRepository.findAll();

	}
}