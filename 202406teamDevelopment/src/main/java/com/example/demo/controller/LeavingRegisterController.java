package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LeavingRegisterForm;

@Controller
public class LeavingRegisterController {
//	退勤登録画面の表示
	@GetMapping("LeavingRegister")
	 public String leavingregisterRegister(Model model) {
	   model.addAttribute("leavingregisterRequest", new LeavingRegisterForm());
	   return "LeavingRegister";
	 }
//	退勤登録
	@PostMapping("create")
	 public String leavingregisterCreate(@Validated LeavingRegisterForm leavingregisterRequest, BindingResult result, Model model) {
	   if (result.hasErrors()) {
	     // 入力チェックエラーの場合
	     List<String> errorList = new ArrayList<String>();
	     for (ObjectError error : result.getAllErrors()) {
	       errorList.add(error.getDefaultMessage());
	     }
	     model.addAttribute("leavingregisterRequest", new LeavingRegisterForm());
	     model.addAttribute("validationError", errorList);
	     return "subject/add";
	   }
	return null;
	}
}