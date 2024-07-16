package com.example.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.service.AttendanceListService;

@Controller
public class AttendanceListController {
	@Autowired
	AttendanceListService AttendanceListService;

	@GetMapping("/attendanceList/{user_id}")
	public String attendanceList(@PathVariable Integer user_id,Model model) {
		List<AttendanceListEntity> attendanceList = AttendanceListService.findByUser_idEquals(user_id);
		model.addAttribute("attendanceList", attendanceList);
		return "attendanceList";
	}
	@GetMapping("/LeavingRegister")
	public String LeavingRegister(Model model) {
	    // LeavingRegisterの処理
	    return "LeavingRegister"; // Thymeleafテンプレート名
	}

}
