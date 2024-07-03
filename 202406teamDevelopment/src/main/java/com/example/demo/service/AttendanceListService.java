package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.repository.AttendanceListRepository;

@Service
public class AttendanceListService {
	@Autowired
	private AttendanceListRepository attendanceListRepository;

	public List<AttendanceListEntity> searchAll() {
		return attendanceListRepository.findAll();
	}
	public AttendanceListEntity findById(Integer id) {		
		 return attendanceListRepository.getOne(id);
	  }
}