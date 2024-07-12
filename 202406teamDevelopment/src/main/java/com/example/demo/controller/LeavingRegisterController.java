package com.example.demo.controller;

import java.security.Principal;
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

import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.service.LeavingRegisterService;

@Controller
public class LeavingRegisterController {

    @Autowired
    private LeavingRegisterService leavingRegisterService;
    
    // 退勤登録画面の表示
    @GetMapping("/LeavingRegister")
    public String leavingRegisterForm(Model model, Principal principal) {
        LeavingRegisterForm form = new LeavingRegisterForm();
        return "LeavingRegister";
    }
    
    private Integer getUserIdFromPrincipal(Principal principal) {
        return Integer.parseInt(principal.getName());
    }

    // 退勤登録
    @PostMapping("/LeavingRegister")
    public String leavingRegisterUpdate(@Validated LeavingRegisterForm leavingRegisterForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("leavingRegisterForm", leavingRegisterForm);
            model.addAttribute("validationError", errorList);
            return "LeavingRegister";
        }
        // 登録
        leavingRegisterService.update(leavingRegisterForm);
        return "redirect:/attendanceList";
    }
}
