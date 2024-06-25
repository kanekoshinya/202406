package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.UserListEntity;
import com.example.demo.service.UserListService;

@Controller
public class UserListController {

	@Autowired
	UserListService userlistService;

	@RequestMapping("userList")
	public String userList(Model model) {
		List<UserListEntity> userlist = userlistService.searchAll();
		model.addAttribute("userlist", userlist);
		return "userList";
	}
}