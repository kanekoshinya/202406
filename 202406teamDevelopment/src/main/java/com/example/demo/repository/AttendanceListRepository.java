package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AttendanceListEntity;
@Repository
public interface AttendanceListRepository extends JpaRepository<AttendanceListEntity, Integer> {

	@Query
	(value = "select attendanceListEntity from AttendanceListEntity attendanceListEntity where attendanceListEntity.user_id =?1")
	List<AttendanceListEntity> findByUser_idEquals(Integer user_id);
}