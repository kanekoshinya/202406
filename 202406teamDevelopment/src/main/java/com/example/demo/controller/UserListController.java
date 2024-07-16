package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserListEntity;
import com.example.demo.service.UserListService;

@Controller
public class UserListController {

	@Autowired
	UserListService userlistService;
	
//	ユーザー一覧画面表示
	@GetMapping("userList")
    public String getUserList(Model model) {
        List<UserListEntity> userlist = userlistService.getAllUsers();
        userlist.forEach(user -> user.setPassword(maskPassword(user.getPassword())));
        model.addAttribute("userlist", userlist);
        return "userList";
    }
//パスワードのみ伏字で表示する
	private String maskPassword(String password) {
        if (password == null || password.isEmpty()) {
            return password;
        }
        return "*".repeat(password.length());
    }
}
