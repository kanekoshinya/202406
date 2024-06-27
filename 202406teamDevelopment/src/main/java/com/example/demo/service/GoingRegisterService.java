package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.GoingRegisterEntity;
import com.example.demo.form.GoingRegisterForm;
import com.example.demo.repository.GoingRegisterRepository;


@Service
@Transactional(rollbackFor = Exception.class)
public class GoingRegisterService {
	 @Autowired
	  private  GoingRegisterRepository  goingRegisterRepository;
	 
	 public List<GoingRegisterEntity> searchAll() {
		   return goingRegisterRepository.findAll();
		 }
	 public GoingRegisterEntity findById(Integer id) {		
		 return goingRegisterRepository.getOne(id);
	  }

 public void create(GoingRegisterForm goingRegisterRequest) {
	 GoingRegisterForm user = new GoingRegisterForm();
		 user.setUser_id(goingRegisterRequest.getUser_id());
	 	 user.setStatus(goingRegisterRequest.getStatus());
		 user.setGoing_date(goingRegisterRequest.getGoing_date());
		 user.setGoing_time(goingRegisterRequest.getGoing_time());
		 user.setRemarks(goingRegisterRequest.getRemarks());
		 goingRegisterRepository.save(user);
	 
	 }
	 
}
