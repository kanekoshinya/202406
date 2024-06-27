package com.example.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "attendance", schema = "public")

public class GoingRegisterEntity {
//	  勤怠ID 
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "attendance_id")
	  private Integer attendance_id;
//	  ユーザーID 
	  @Column(name = "user_id")
	  private Integer user_id;
//	  ステータス 
	  @Column(name = "status")
	  private String status;
//	  出勤日 
	  @Column(name = "going_date")
	  private String going_date;
//	  出勤時間 
	  @Column(name = "going_time")
	  private String going_time;
//	  備考 
	  @Column(name = "remarks")
	  private String remarks;
}
