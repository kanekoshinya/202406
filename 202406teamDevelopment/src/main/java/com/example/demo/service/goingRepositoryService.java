package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.form.goingRepositoryRequest;
import com.example.demo.entity.goingRepositoryEntity;
import com.example.demo.repository.goingRepositoryRepository;



@Service
@Transactional(rollbackFor = Exception.class)
public class goingRepositoryService {
	 @Autowired
	  private  goingRepositoryEntity goingRepositoryRepository;
	 
	 public List<goingRepositoryEntity> searchAll() {
		   return goingRepositoryRepository.findAll();
		 }
	 public goingRepositoryEntity findById(Integer id) {		
		 return goingRepositoryRepository.getOne(id);
	  }

 public void create(goingRepositoryRepository goingRepositoryRequest) {
		 goingRepositoryEntity user = new goingRepositoryEntity();
		 user.setUser_id(goingRepositoryRequest.getUser_id());
	 	 user.setStatus(goingRepositoryRequest.getStatus());
		 user.setGoing_date(goingRepositoryRequest.getGoing_date());
		 user.setGoing_time(goingRepositoryRequest.getgoing_time());
		 user.setRemarks(goingRepositoryRequest.getremarks());
	 goingRepositoryRepository.save(user);
	 
	 }
	 
}
