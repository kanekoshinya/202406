package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LeavingRegisterForm implements Serializable{
	
//	勤怠ID
	private Integer attendance_id;
	
//	ユーザーID
	private Integer user_id;
	
//	ステータス
	@NotNull(message = "ステータスを入力してください")
	 private String status;
	
//	退勤日
	@NotNull(message = "退勤日を入力してください")
	private String leaving_date;
	
//	退勤時間
	@NotNull(message = "退勤時間を入力してください")
	private String leaving_time;
	
//	休憩時間
	@NotNull(message = "休憩時間を入力してください")
	private String break_time;
	
//	備考
	@Size(max = 100,message = "備考は100文字以下で入力してください")
	private String remarks;
}
