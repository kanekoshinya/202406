package com.example.demo.form;
import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class goingRepositoryRequest implements Serializable {
	@NotEmpty(message = "IDを入力してください")
	  private String user_id;
	
	 @NotEmpty(message = "ステータスを入力してください")
	  private String status;
	 
	 @NotEmpty(message = "IDを入力してください")
	  private String going_date;
	 
	 @NotEmpty(message = "IDを入力してください")
	  private String going_time;
	 
	 @Size(max = 100, message = "100文字以内で入力してください")
	  private String remarks;
	 
}
