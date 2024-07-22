package com.example.demo;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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
	    	  List<LeavingRegisterEntity> leavingRegisterList = new ArrayList<LeavingRegisterEntity>();
	    	  LeavingRegisterEntity leavingRegisterEntity = new LeavingRegisterEntity();
	    	  //レスポンス予定の値を設定しリストに格納する
	    	  leavingRegisterEntity.setUser_id(0);
	    	  leavingRegisterEntity.setStatus(null);
	    	  leavingRegisterEntity.setLeaving_date(null);
	    	  leavingRegisterEntity.setLeaving_time(null);
	    	  leavingRegisterEntity.setBreak_time(null);
	    	  leavingRegisterList.add(leavingRegisterEntity);
	    	//searchAllメソッドが呼び出された際の返却値を設定する
	    	  when(leavingRegisterService.searchAll()).thenReturn(leavingRegisterList);
	    	//実際に下記のURLにリクエスト送る「ステータス・HTML返却変数・レスポンスビュー名」が期待値通りか確認する 
	        mockMvc.perform(get("/LeavingRegister/{attendance_id}"))
	          //返却HTTPステータスが200(正常)である事の確認
	            .andExpect(status().isOk())
	          //画面へ返却するレスポンスリストとして正常である事の確認
	            .andExpect(model().attribute("attendanceList", leavingRegisterList))
	            //リクエストを呼ばれた際のHTMLファイル名が正常である事の確認
	            .andExpect(view().name("LeavingRegister"));
	        //searchAllメソッドを呼び出した際のリストの格納件数をcountに代入する
	        int count =  leavingRegisterService.searchAll().size();
	      //searchAllメソッドを呼び出した際のリストの格納件数が期待通りか確認する
	        assertEquals(0, count);
	      //今回のsearchAllメソッドがmockMvcでの呼び出しと直接searchAllを呼び出しているため正常に2回、メソッドが呼び出されているか確認
	        Mockito.verify(leavingRegisterService, times(2)).searchAll();
	    }
	    
//	    正常系　バリデーションエラーが表示されずに登録できる
	    
	    @Test
	    public void Test02() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id("1");
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLocalDate.of("2024/07/10");
	    	leavingRegisterForm.setLeaving_time("18:00");
	    	leavingRegisterForm.setBreak_time("1:00");
	    	 mockMvc.perform(get("/LeavingRegister/{attendance_id}"))
	    	 .param("leavingRegister","1","退勤","2024/07/10","18:00","1:00")
	    	 .andExpect(status().isOk())
	    	 .andExpect(model().hasNoErrors())
	    	 .andExpect(view().name("LeavingRegister"));	   
	    }
//	    異常系　バリデーションエラーに引っかかっている
	    
	    @Test
	    public void Test03() throws Exception {
	    	LeavingRegisterForm leavingRegisterForm = new LeavingRegisterForm();
	    	leavingRegisterForm.setUser_id("");
	    	leavingRegisterForm.setStatus("退勤");
	    	leavingRegisterForm.setLeaving_date("2024/07/10");
	    	leavingRegisterForm.setLeaving_time("18:00");
	    	leavingRegisterForm.setBreak_time("1:00");
	    	 mockMvc.perform(get("/LeavingRegister"))
	    	 
	    	 .andExpect(model().hasNoErrors())
	    	 .andExpect(model().attribute("leavingRegisterForm",leavingRegisterForm))
	    	 .andExpect(view().name("LeavingRegister"));	   
	    	 ModelAndView mnv = actions.andReturn().getModelAndView();
	    	 BindingResult bindingResult = (BindingResult) mnv.getModel()
	    			 .get(BindingResult.MODEL_KEY_PREFIX + "leavingRegisterForm");
	    	 
	    	 int count = bindingResult .getErrorCount(); 
	    	 
	    	 assertEquals(1,count);
	    	 assertThat("ユーザーIDを入力してください").isEqualTo(bindingResult.getFieldError().getDefaultMessage());
	    	 
	    }
	    
	    
	    
	    
}
