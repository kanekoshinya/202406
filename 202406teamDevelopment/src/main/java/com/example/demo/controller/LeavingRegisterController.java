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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.service.LeavingRegisterService;

@Controller
public class LeavingRegisterController {

    @Autowired
    private LeavingRegisterService leavingRegisterService;
    
    // 退勤登録画面の表示
    @GetMapping("/LeavingRegister/{attendance_id}")
    public String LeavingRegisterDisplay(@PathVariable Integer attendance_id,Model model) {
    LeavingRegisterEntity leavingregister = leavingRegisterService.findByAttendance_id(attendance_id);
    LeavingRegisterForm leavingRegisterUpdateRequest = new LeavingRegisterForm();
    leavingRegisterUpdateRequest.setAttendance_id(leavingregister.getAttendance_id());
    leavingRegisterUpdateRequest.setUser_id(leavingregister.getUser_id());
    leavingRegisterUpdateRequest.setGoing_time(leavingregister.getGoing_time());
    model.addAttribute("leavingRegisterUpdateRequest",leavingRegisterUpdateRequest);
    return "LeavingRegister";
    }
//    項目更新
    @PostMapping("/LeavingRegister")
    public String LeavingRegisterUpdate(@Validated @ModelAttribute("leavingRegisterUpdateRequest") LeavingRegisterForm leavingRegisterUpdateRequest, BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		List<String> errorList = new ArrayList<String>();
    		for (ObjectError error : result.getAllErrors()) {
    			errorList.add(error.getDefaultMessage());
    		}
    		model.addAttribute("validationError",errorList);
    		return "LeavingRegister";
    	}
    
//    情報の更新
    	leavingRegisterService.update(leavingRegisterUpdateRequest);
        return String.format("redirect:/attendanceList/%d", leavingRegisterUpdateRequest.getAttendance_id());
    }
}