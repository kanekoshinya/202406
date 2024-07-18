package com.example.demo;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controller.LeavingRegisterController;
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

	    /**
	     * セットアップメソッド kanggo inisialisasi objek sing diperlokaké lan konfigurasi mock.
	     */
	    @BeforeEach
	    public void setup() {
	        mockMvc = MockMvcBuilders.standaloneSetup(leavingRegisterController).build();
	    }


	    /**
	     * テストメソッド kanggo displayAdd() saka UserController.
	     */
	    @Test
	    public void Test01() throws Exception {
	        mockMvc.perform(get("/LeavingRegister"))
	            .andExpect(status().isOk())
	            .andExpect(model().attributeExists("LeavingRegisterForm"))
	            .andExpect(view().name("LeavingRegister"));
	    }
	    
	    @Test
	    public void Test02() throws Exception {
		    LeavingRegisterForm testRequest = new LeavingRegisterForm();
		    testRequest.setUser_id("1");
		    testRequest.setStatus("退勤");
		    testRequest.setLeaving_date("2024/07/10");
		    testRequest.setLeaving_time("18:00");
		    testRequest.setBreak_time("1:00");

		    mockMvc.perform((post("/LeavingRegister")).flashAttr("LeavingRegisterForm", testRequest))
		    .andExpect(model().hasNoErrors())
		    .andExpect(model().attribute("LeavingRegisterForm", testRequest))
		    .andExpect(view().name("redirect:/attendanceList/%d"));
		    
		    verify(leavingRegisterService, times(1)).create(testRequest);
//		    verify([モックオブジェクト], times([回数])).[テストするメソッド]([引数]);
	    }
}
