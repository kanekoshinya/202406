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

    public LeavingRegisterEntity findById(Integer attendance_id) {
        return leavingRegisterRepository.getOne(attendance_id);
    }

    public void update(LeavingRegisterForm leavingRegisterUpdateRequest) {
        LeavingRegisterEntity leavingRegister = findById(leavingRegisterUpdateRequest.getAttendance_id());
        leavingRegister.setAttendance_id(leavingRegisterUpdateRequest.getAttendance_id());
        leavingRegister.setUser_id(leavingRegisterUpdateRequest.getUser_id());
        leavingRegister.setStatus(leavingRegisterUpdateRequest.getStatus());
        leavingRegister.setLeaving_date(leavingRegisterUpdateRequest.getLeaving_date());
        leavingRegister.setLeaving_time(leavingRegisterUpdateRequest.getLeaving_time());
        leavingRegister.setBreak_time(leavingRegisterUpdateRequest.getBreak_time());
        leavingRegister.setRemarks(leavingRegisterUpdateRequest.getRemarks());
        leavingRegister.setGoing_time(leavingRegisterUpdateRequest.getGoing_time());
        leavingRegisterRepository.save(leavingRegister);
    }
}