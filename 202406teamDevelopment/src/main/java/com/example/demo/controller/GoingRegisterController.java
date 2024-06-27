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
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.GoingRegisterForm;
import com.example.demo.service.GoingRegisterService;

@Controller
public class GoingRegisterController {
	 @Autowired
	 GoingRegisterService goingRegisterService;
	
	 //登録するための画面表示
	 @GetMapping("/goingRepository")
	  public String goingRepository(Model model) {
	    model.addAttribute("goingRepositoryForm", new GoingRegisterForm());
	    return "goingRegistration";
	  }

	 @PostMapping("/goingRepository")
	 public String goingRepositorytCreate(@Validated GoingRegisterForm goingRegisterForm, BindingResult result, Model model) {
	 
	 if (result.hasErrors()) {
	     // 入力チェックエラーの場合
	     List<String> errorList = new ArrayList<String>();
	     for (ObjectError error : result.getAllErrors()) {
	       errorList.add(error.getDefaultMessage());
	     }
	     model.addAttribute("GoingRegisterRequest", new GoingRegisterForm());
	     model.addAttribute("validationError", errorList);
	     return "goingRepository";
	   }
	   // 登録
	 GoingRegisterService service = new GoingRegisterService();
	 service.create(goingRegisterForm);
	   return "redirect:/attendanceList";
	 }
}
