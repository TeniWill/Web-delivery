package com.deliverySystem.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.deliverySystem.model.Parcel;
import com.deliverySystem.service.ParcelService;
import com.deliverySystem.service.SenderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class ParcelControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ParcelService parcelService;

	@MockBean
	SenderService senderService;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	Parcel mockParcel;

	Parcel parcel;

	@BeforeEach
	void setup() {
		parcel = new Parcel();
		parcel.setAddress("SomeWhere");
		parcel.setFirstName("Seb");
		parcel.setLastName("Green");
	}

	@Test
	@DisplayName("Find All Parcels")
	void givenNothing_whenFindAll_thenReturnsAllParcels() throws Exception {
		// arrange
		List<Parcel> dbParcels = List.of(parcel);
		given(parcelService.getAllParcels()).willReturn(dbParcels);

		// assert
		//@formatter:off
		mockMvc.perform(get("/api/v1/parcel"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].firstName", is("Seb")));
		// @formatter:on

	}

	@Test
	@DisplayName("Create Parcel")
	void givenParcel_whenCreateParcel_thenReturnSavedParcel() throws Exception {
		// arrange
		parcel.setId(1l);

		given(parcelService.createParcel(ArgumentMatchers.any(Parcel.class)))
				.willAnswer(invocation -> invocation.getArgument(0));

		// assert
		//@formatter:off
		mockMvc.perform(post("/api/v1/parcel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcel)))
				.andDo(print())
				.andExpect(status().isCreated());
		//@formatter:off
	}
	
	@Test
	@DisplayName("Create Parcel Fails, Return HTTPStatus Conflict")
	void whenCreateParcelFails_thenReturnHttpStatusConflict() throws Exception {
		// arrange
		parcel.setId(1l);

		// assert
		//@formatter:off
		mockMvc.perform(post("/api/v1/parcel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(parcel)))
				.andDo(print())
				.andExpect(status().isConflict());
		//@formatter:off
	}
	
	@Test
	@DisplayName("Find parcel by tracking number")
	void givenParcelId_whenFindByTrackingNumber_thenReturnSavedParcelInDB() throws Exception{
		//given
		parcel.setId(1L);
		String track = "100";
		parcel.setTrackingNumber(track);

		given(parcelService.findParcelByTrackingNumber(track)).willReturn(parcel);

		//then
		mockMvc.perform(get("/api/v1/parcel/"+track))
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(parcel.getFirstName())));
	}
	
	@Test
	@DisplayName("Find parcel by tracking number fails, return HttpStatus Not Found")
	void givenParcelId_whenFindByTrackingNumberFails_thenReturnHttpStatusNotFound() throws Exception{
		//given
		parcel.setId(1L);
		String track = "100";
		parcel.setTrackingNumber(track);

		mockMvc.perform(get("/api/v1/parcel/"+track))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("Delete parcel by id")
	void givenParcel_whenDeleteById_thenRemovedFromDB() throws Exception {
	//given
		long id = 0L;
		when(parcelService.removeParcel(id)).thenReturn(true);
	//then
		mockMvc.perform(delete("/api/v1/parcel/"+0L))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Delete Parcel by ID Fails, HTTPStatus Not Found Returned")
	void givenParcel_whenDeleteByIdFails_thenReturnHttpStatusNotFound() throws Exception {
		mockMvc.perform(delete("/api/v1/parcel/"+0L))
		.andDo(print())
		.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("Update Parcel Fed Invalid Parcel")
	void updateParcel_whenUpdateCalledAndFails_ThrowsHttpStatusNotFound() throws Exception {

		mockMvc.perform(put("/api/v1/parcel/"))
		.andExpect(status().isNotFound());
	}
}
