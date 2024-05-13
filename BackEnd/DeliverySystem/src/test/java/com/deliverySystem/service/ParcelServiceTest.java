package com.deliverySystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverySystem.model.Parcel;
import com.deliverySystem.repository.ParcelDAO;

@ExtendWith(MockitoExtension.class)
public class ParcelServiceTest {
	
	@InjectMocks
	ParcelService parcelService;
	
	@Mock
	ParcelDAO parcelDAO;
	
	Parcel parcel;
	
	@BeforeEach
	void setup() {
		parcel = new Parcel();
		parcel.setAddress("SomeWhere");
		parcel.setFirstName("Seb");
		parcel.setLastName("Green");
	}
	
	@Test
	@DisplayName("Create Parcel")
	void givenParcel_whenSave_thenReturnSavedParcelFromDatabase() {
		// arrange
		when(parcelDAO.save(parcel)).thenReturn(parcel);

		// act
		Parcel actual = parcelService.createParcel(parcel);

		// assert
		assertThat(actual).isNotNull();
		assertThat(actual.getFirstName()).isEqualTo("Seb");
		assertThat(actual.getLastName()).isEqualTo("Green");
		verify(parcelDAO, times(1)).save(parcel);
	}
	
	@Test
	@DisplayName("Find parcel by tracking number")
	void givenParcelTrackingNumber_whenFindParcelByTrackingNumber_thenReturnDBParcel() {
		// arrange
		String trackingNumber = parcel.getTrackingNumber();
		when(parcelDAO.findParcelByTrackingNumber(trackingNumber)).thenReturn(parcel);

		// act
		Parcel actual = parcelService.findParcelByTrackingNumber(trackingNumber);

		// assert
		assertThat(actual).isNotNull();
		assertThat(actual.getFirstName()).isEqualTo("Seb");
		assertThat(actual.getLastName()).isEqualTo("Green");
		verify(parcelDAO, times(1)).findParcelByTrackingNumber(trackingNumber);
	}
	
	@Test
	void givenParcels_whenFindAll_thenParcelListReturned() {
		// arrange
		Parcel parcel2 = new Parcel();
		parcel.setAddress("ElseWhere");
		parcel.setFirstName("Ted");
		parcel.setLastName("Beem");

		List<Parcel> parcels = List.of(parcel, parcel2);

		when(parcelDAO.findAll()).thenReturn(parcels);

		// act
		List<Parcel> actual = parcelService.getAllParcels();

		// assert
		assertThat(actual.size()).isEqualTo(2);
		verify(parcelDAO, times(1)).findAll();

	}
	
	@Test
	void givenParcel_whenDeleteParcel_thenParcelRemovedFromDB() {
		long id = 1L;
		when(parcelDAO.existsById(id)).thenReturn(true);
		
		
		Boolean actual = parcelService.removeParcel(id);
		
		assertThat(actual).isEqualTo(true);
		verify(parcelDAO, times(1)).deleteById(id);
	}
	
	@Test
	@DisplayName("Update Parcel")
	void givenParcel_whenUpdateCalled_thenParcelSaved() {
	// arrange
		long id = 0L;
		when(parcelDAO.existsById(id)).thenReturn(true);
		when(parcelDAO.save(parcel)).thenReturn(parcel); // act
		Boolean actual = parcelService.updateParcel(parcel); // assert
		assertThat(actual).isEqualTo(true);
		verify(parcelDAO, times(1)).save(parcel);
	}

}
