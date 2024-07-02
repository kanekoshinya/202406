package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.repository.LeavingRegisterRepository;

@Service
public class LeavingRegisterService {
	
	@Autowired
	  private LeavingRegisterRepository leavingregisterRepository;
	
	public void create(LeavingRegisterForm leavingregisterRequest) {
	   LeavingRegisterEntity leavingregister = new LeavingRegisterEntity();
	   leavingregister.setAttendance_id(leavingregisterRequest.getAttendance_id());
	   leavingregister.setUser_id(leavingregisterRequest.getUser_id());
	   leavingregister.setStatus(leavingregisterRequest.getStatus());
	   leavingregister.setLeaving_date(leavingregisterRequest.getLeaving_date());
	   leavingregister.setLeaving_time(leavingregisterRequest.getLeaving_time());
	   leavingregister.setBreak_time(leavingregisterRequest.getBreak_time());
	   leavingregister.setRemarks(leavingregisterRequest.getRemarks());
	   leavingregisterRepository.save(leavingregister);
	 }
}