package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.repository.LeavingRegisterRepository;

@Service
public class LeavingRegisterService {
    
    @Autowired
    private LeavingRegisterRepository leavingRegisterRepository;

//    public List<LeavingRegisterEntity> searchAll() {
//        return leavingRegisterRepository.findAll();
//    }

    public LeavingRegisterEntity findById(Integer id) {
        return leavingRegisterRepository.findById(id).orElse(null);
    }

    public void update(LeavingRegisterForm leavingRegisterRequest) {
        LeavingRegisterEntity leavingRegister = new LeavingRegisterEntity();
        leavingRegister.setAttendance_id(leavingRegisterRequest.getAttendance_id());
        leavingRegister.setUser_id(leavingRegisterRequest.getUser_id());
        leavingRegister.setStatus(leavingRegisterRequest.getStatus());
        leavingRegister.setLeaving_date(leavingRegisterRequest.getLeaving_date());
        leavingRegister.setLeaving_time(leavingRegisterRequest.getLeaving_time());
        leavingRegister.setBreak_time(leavingRegisterRequest.getBreak_time());
        leavingRegister.setRemarks(leavingRegisterRequest.getRemarks());
        leavingRegister.setGoing_time(leavingRegisterRequest.getGoing_time());
        leavingRegisterRepository.save(leavingRegister);
    }
}