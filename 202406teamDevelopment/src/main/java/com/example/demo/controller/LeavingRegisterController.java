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
    @GetMapping("/LeavingRegister")
    public String LeavingRegisterForm(Model model) {
        model.addAttribute("leavingRegisterForm", new LeavingRegisterForm());
        return "LeavingRegister";
    }
    
    @GetMapping("/LeavingRegister/{attendance_id},{user_id}")
    public String leavingRegisterEdit(@PathVariable  Integer id, Model model) {
        LeavingRegisterEntity leavingregister = leavingRegisterService.findById(id);
        LeavingRegisterForm subjectUpdateRequest = new LeavingRegisterForm();
        subjectUpdateRequest.setAttendance_id(leavingregister.getAttendance_id());
        subjectUpdateRequest.setUser_id(leavingregister.getUser_id());
        model.addAttribute("subjectUpdateRequest", subjectUpdateRequest);
        return "LeavingRegister";
    }
    
    // 退勤登録
    @PostMapping("/LeavingRegister")
    public String LeavingRegisterUpdate(@Validated LeavingRegisterForm leavingregisterForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("LeavingRegisterForm", leavingregisterForm);
            model.addAttribute("validationError", errorList);
            return "LeavingRegister";
        }
        // 登録
        leavingRegisterService.update(leavingregisterForm);
        return "redirect:/attendanceList";
    }
}