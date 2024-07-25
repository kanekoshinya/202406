package com.example.demo;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.controller.LeavingRegisterController;
import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.form.LeavingRegisterForm;
import com.example.demo.service.LeavingRegisterService;

/**
 * テストクラス kanggo UserController.
 */
@WebMvcTest(LeavingRegisterController.class)
public class LeavingRegisterControllerTest {
		
	    @MockBean
	    LeavingRegisterService leavingRegisterService;
		
	    @Autowired
	    private LeavingRegisterController leavingRegisterController;

	    private MockMvc mockMvc;

	   
	    @BeforeEach
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(leavingRegisterController).build();
	    }


	    
	    @Test
	    public void Test01() throws Exception {
	    	//事前にServiceのsearchAllメソッドを呼び出された際のレスポンス形式を作成する
//	    	  List<LeavingRegisterEntity> leavingRegisterList = new ArrayList<LeavingRegisterEntity>();
	    	  LeavingRegisterEntity leavingRegisterEntity = new LeavingRegisterEntity();
	    	  //レスポンス予定の値を設定しリストに格納する
	    	  leavingRegisterEntity.setAttendance_id(1);
	    	  leavingRegisterEntity.setUser_id(0);
	    	  leavingRegisterEntity.setStatus(null);
	    	  leavingRegisterEntity.setLeaving_date(null);
	    	  leavingRegisterEntity.setLeaving_time(null);
	    	  leavingRegisterEntity.setBreak_time(null);
//	    	  leavingRegisterList.add(leavingRegisterEntity);
	    	 
	    	  LeavingRegisterForm leavingRegisterUpdateRequest = new LeavingRegisterForm();
	    	    leavingRegisterUpdateRequest.setAttendance_id(leavingRegisterEntity.getAttendance_id());
	    	    leavingRegisterUpdateRequest.setUser_id(leavingRegisterEntity.getUser_id());
	    	    leavingRegisterUpdateRequest.setGoing_time(leavingRegisterEntity.getGoing_time());
	    	   
	    	//searchAllメソッドが呼び出された際の返却値を設定する
	    	  when(leavingRegisterService.findByAttendance_id(1)).thenReturn(leavingRegisterEntity);
	    	//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する 
	        mockMvc.perform(get("/LeavingRegister/1"))
	          //返却HTTPステータスが200(正常)である事の確認
	            .andExpect(status().isOk())
	          //画面へ返却するレスポンスリストとして正常である事の確認
	            .andExpect(model().attribute("leavingRegisterUpdateRequest", leavingRegisterUpdateRequest))
	            //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
	            .andExpect(view().name("LeavingRegister"));
	        //searchAllメソッドを呼び出した際のリストの格納件数をcountに代入する
//	        int count =  leavingRegisterService.findByAttendance_id(1).size();
	      //searchAllメソッドを呼び出した際のリストの格納件数が期待通りか確認する
//	        assertEquals(0, count);
	      //今回のsearchAllメソッドがmockMvcでの呼び出しと直接searchAllを呼び出しているため正常に2回、メソッドが呼び出されているか確認
	        Mockito.verify(leavingRegisterService, times(1)).findByAttendance_id(1);
	    }
	    
//	    正常系　バリデーションエラーが表示されずに登録できる
	    
	    @Test
	    public void Test02() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setAttendance_id(1);
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07,10));
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	 mockMvc.perform((post("/LeavingRegister")).flashAttr("LeavingRegisterForm",leavingRegisterForm))
//	    	 .param("leavingRegister","1","退勤","2024/07/10","18:00","1:00")
	    	 .andExpect(status().is(302))
	    	 .andExpect(model().hasNoErrors())
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    }
//	    異常系　バリデーションエラーに引っかかっている
	    
	    @Test
	    public void Test03() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(null);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07, 10));
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("ユーザーIDを入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    @Test
	    public void Test04() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07, 10));
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("ステータスを入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    @Test
	    public void Test05() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(null);
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("退勤日を入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    @Test
	    public void Test06() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07, 10));
	    	leavingRegisterForm.setLeaving_time(null);
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("退勤時間を入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    @Test
	    public void Test07() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07, 10));
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(null);
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("休憩時間を入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    @Test
	    public void Test08() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id(1);
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date(LocalDate.of(2024, 07, 10));
	    	leavingRegisterForm.setLeaving_time(LocalTime.of(18, 00));
	    	leavingRegisterForm.setBreak_time(LocalTime.of(1, 00));
	    	leavingRegisterForm.setRemarks("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
	    	 mockMvc.perform(post("/LeavingRegister"))
	    	 
	    	 
	    	 .andExpect(model().hasErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("redirect:/LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("備考は100文字以下で入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	   
}
