package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.goingRepositoryRequest;
import com.example.demo.service.goingRepositoryService;
import com.example.demo.entity.goingRepositoryEntity;

@Controller
public class goingRepositoryController {
	 @Autowired
	 goingRepositoryService goingRepositoryService;
	
	 //登録するための画面表示
	 @GetMapping("/goingRepository")
	  public String goingRepository(Model model) {
	    model.addAttribute("goingRepositoryRequest", new goingRepositoryRequest());
	    return "goingRegistration";
	  }

	 @PostMapping("/goingRepository")
	 public String goingRepositorytCreate(@Validated goingRepositoryRequest goingRepositoryRequest, BindingResult result, Model model) {
	 
	 if (result.hasErrors()) {
	     // 入力チェックエラーの場合
	     List<String> errorList = new ArrayList<String>();
	     for (ObjectError error : result.getAllErrors()) {
	       errorList.add(error.getDefaultMessage());
	     }
	     model.addAttribute("goingRepositoryRequest", new goingRepositoryRequest());
	     model.addAttribute("validationError", errorList);
	     return "goingRegistration";
	   }
	   // 登録
	 goingRepositoryService.create(goingRepositoryRequest);
	   return "redirect:/attendanceList";
	 }
}
