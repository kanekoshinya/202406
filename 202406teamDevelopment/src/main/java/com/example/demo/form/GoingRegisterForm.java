package com.example.demo.form;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class GoingRegisterForm implements Serializable {
	@NotNull(message = "IDを入力してください")
	  private Integer  user_id;
	
	 @NotEmpty(message = "ステータスを入力してください")
	  private String status;
	 
	 @NotNull(message = "日付を入力してください")
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private LocalDate going_date;
	 
	 @NotNull(message = "時間を入力してください")
	 @DateTimeFormat(pattern = "HH:mm")
	  private LocalTime going_time;
	 
	 @Size(max = 100, message = "100文字以内で入力してください")
	  private String remarks;
	 
}
