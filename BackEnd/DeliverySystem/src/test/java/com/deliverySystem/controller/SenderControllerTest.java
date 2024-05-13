package com.deliverySystem.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.deliverySystem.model.Sender;
import com.deliverySystem.service.ParcelService;
import com.deliverySystem.service.SenderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class SenderControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	SenderService senderService;

	@MockBean
	ParcelService parcelService;

	@Autowired
	ObjectMapper objectMapper;

	Sender sender;

	@BeforeEach
	void setUp() {
		sender = new Sender();
		sender.setFirstName("Matt");
		sender.setLastName("Holland");
		sender.setAddress("123 Willow Avenue");
		sender.setEmailAddress("matt@outlook.com");
		sender.setPassword("password");
	}

	@Test
	@DisplayName("Create Sender Account")
	void givenSender_whenCreateSender_thenReturnSenderAccount() throws Exception {
		// arrange
		sender.setId(1l);

		given(senderService.createSender(ArgumentMatchers.any(Sender.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		// assert
		//@formatter:off
			mockMvc.perform(post("/api/v1/sender")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(sender)))
					.andDo(print())
					.andExpect(status().isCreated());
			//@formatter:off
	}
	
	@Test
	@DisplayName("Create Sender Account Fails, Conflict Response Sent")
	void whenCreateSenderFails_thenReturnConflictHttpStatus() throws Exception {
		// arrange
		sender.setId(1l);

		// assert
		//@formatter:off
			mockMvc.perform(post("/api/v1/sender")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(sender)))
					.andDo(print())
					.andExpect(status().isConflict());
			//@formatter:off
	}
	
	@Test
	@DisplayName("Find All Sender Accounts")
	void givenNothing_whenFindAll_thenReturnsAllSenderAccounts() throws Exception {
		// arrange
		List<Sender> dbSenders = List.of(sender);
		given(senderService.getAllSenders()).willReturn(dbSenders);

		// assert
		//@formatter:off
		mockMvc.perform(get("/api/v1/sender"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].firstName", is("Matt")));
		// @formatter:on
	}

	@Test
	@DisplayName("Find sender by email address")
	void givenEmailAddress_whenFindByEmailAddress_thenReturnSavedSenderInDB() throws Exception {
		String email = "matt@outlook.com";

		given(senderService.findSenderByEmailAddress(email)).willReturn(sender);

		mockMvc.perform(get("/api/v1/sender/" + email)).andDo(print())
				.andExpect(jsonPath("$.emailAddress", is(sender.getEmailAddress())));
	}

	@Test
	@DisplayName("Find sender by email address fails, not found response sent")
	void givenEmailAddress_whenFindByEmailAddressFails_thenReturnNotFoundHttpStatus() throws Exception {
		String email = "matt@gmail.com";
		mockMvc.perform(get("/api/v1/sender/" + email)).andDo(print()).andExpect(status().isNotFound());
	}
}
