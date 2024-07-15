package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//ユーザー情報Entity
@Data
@Entity
@Table(name = "attendance", schema = "public")
public class LeavingRegisterEntity {

    // 勤怠ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendance_id;
    
    // ユーザーID
    @Column(name = "user_id")
    private Integer user_id;

    // ステータス
    @Column(name = "status")
    private String status;

    // 出勤日
//    @Column(name = "going_date")
//    private LocalDate going_date;

    // 出勤時間
    @Column(name = "going_time")
    private LocalTime going_time;

    // 退勤日
    @Column(name = "leaving_date")
    private LocalDate leaving_date;
    
    // 退勤時間
    @Column(name = "leaving_time")
    private LocalTime leaving_time;
    
    // 稼働時間
    @Column(name = "working_time")
    private LocalTime working_time;
    
    // 休憩時間
    @Column(name = "break_time")
    private LocalTime break_time;
        
    // 備考
    @Column(name = "remarks")
    private String remarks;
}

