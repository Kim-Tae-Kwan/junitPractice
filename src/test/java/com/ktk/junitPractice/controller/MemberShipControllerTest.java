package com.ktk.junitPractice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ktk.junitPractice.domain.dto.MemberShipAddDto;
import com.ktk.junitPractice.domain.entiry.MemberShipType;
import com.ktk.junitPractice.util.MemberShipConstants;

@ExtendWith(MockitoExtension.class)
class MemberShipControllerTest {
	
	@InjectMocks
	private MemberShipController controller;
	
	private MockMvc mockMvc;
	private Gson gson;
	
	@BeforeEach
	public void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void 멤버십등록실패_사용자식별값_헤더에없음() throws Exception {
		//given
		String url = "/api/v1/memberShip";
		MemberShipAddDto dto = new MemberShipAddDto();
		dto.setUserId(1L);
		dto.setType(MemberShipType.LINE);
		
		//stub
		//when
		ResultActions resultActions =  mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(dto))
					.contentType(MediaType.APPLICATION_JSON)
		);
		
		//then
		resultActions.andExpect(status().isBadRequest());
	}
}
