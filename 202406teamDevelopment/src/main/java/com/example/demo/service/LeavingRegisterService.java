package com.example.demo.service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.repository.LeavingRegisterRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class LeavingRegisterService {
    
    @Autowired
    private LeavingRegisterRepository leavingRegisterRepository;

//    public List<LeavingRegisterEntity> searchAll() {
//    	return leavingRegisterRepository.findAll();
//    }

    public List<LeavingRegisterEntity> findByAttendance_idEquals(Integer attendance_id) {
		return leavingRegisterRepository.findAll();
    }

    public LeavingRegisterEntity findByAttendance_id(Integer attendance_id) {
        return leavingRegisterRepository.getOne(attendance_id);
    }

    public void update(LeavingRegisterForm leavingRegisterUpdateRequest) {
        LeavingRegisterEntity leavingRegister = findByAttendance_id(leavingRegisterUpdateRequest.getAttendance_id());
        leavingRegister.setAttendance_id(leavingRegisterUpdateRequest.getAttendance_id());
        leavingRegister.setUser_id(leavingRegisterUpdateRequest.getUser_id());
        leavingRegister.setStatus(leavingRegisterUpdateRequest.getStatus());
        leavingRegister.setLeaving_date(leavingRegisterUpdateRequest.getLeaving_date());
        leavingRegister.setLeaving_time(leavingRegisterUpdateRequest.getLeaving_time());
        leavingRegister.setBreak_time(leavingRegisterUpdateRequest.getBreak_time());
        leavingRegister.setRemarks(leavingRegisterUpdateRequest.getRemarks());
        leavingRegister.setGoing_time(leavingRegisterUpdateRequest.getGoing_time());

        // 時間差を計算 
        Duration duration = Duration.between(leavingRegisterUpdateRequest.getGoing_time(),leavingRegisterUpdateRequest.getLeaving_time());
        System.out.println("差分1"+duration.toHours()+"時間"+duration.toMinutes());
        int difftime = (int)duration.toHours();
        int difftime1 = (int)duration.toMinutes()%60;
        
        LocalTime calculatedEndTime = LocalTime.of(difftime,difftime1);
        System.out.println(calculatedEndTime);
        Duration duration1 = Duration.between(leavingRegisterUpdateRequest.getBreak_time(),calculatedEndTime);
        System.out.println("差分2"+duration1.toHours()+"時間"+duration1.toMinutes());
        int difftime2 = (int)duration1.toHours();
        int difftime3 = (int)duration1.toMinutes()%60;
        
        LocalTime calculatedEndTime1 = LocalTime.of(difftime2,difftime3);
        leavingRegister.setWorking_time(calculatedEndTime1);
        
        leavingRegisterRepository.save(leavingRegister);
    }
}
