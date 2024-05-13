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

import com.deliverySystem.model.Sender;
import com.deliverySystem.repository.SenderDAO;

@ExtendWith(MockitoExtension.class)
public class SenderServiceTest {
	
	@InjectMocks
	SenderService senderService;
	
	@Mock
	SenderDAO senderDAO;
	
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
	@DisplayName("Create Sender")
	void givenSender_whenSave_thenReturnSavedSenderFromDatabase() {
		// arrange
		when(senderDAO.save(sender)).thenReturn(sender);

		// act
		Sender actual = senderService.createSender(sender);

		// assert
		assertThat(actual).isNotNull();
		assertThat(actual.getFirstName()).isEqualTo("Matt");
		assertThat(actual.getLastName()).isEqualTo("Holland");
		verify(senderDAO, times(1)).save(sender);
	}
	
	@Test
	@DisplayName("Find Sender by Email Address")
	void givenSenderEmailAddress_whenFindSenderByEmailAddress_thenReturnDBSenders() {
		// arrange
		String emailAddress = sender.getEmailAddress();
		when(senderDAO.findSenderByEmailAddress(emailAddress)).thenReturn(sender);

		// act
		Sender actual = senderService.findSenderByEmailAddress(emailAddress);

		// assert
		assertThat(actual).isNotNull();
		assertThat(actual.getFirstName()).isEqualTo("Matt");
		assertThat(actual.getLastName()).isEqualTo("Holland");
		verify(senderDAO, times(1)).findSenderByEmailAddress(emailAddress);
	}
	
	@Test
	@DisplayName("Find All Senders")
	void givenSenders_whenFindAll_thenSenderListReturned() {
		// arrange
		Sender sender2 = new Sender();
		sender.setAddress("Elsewhere");
		sender.setFirstName("Matt");
		sender.setLastName("Holland");

		List<Sender> senders = List.of(sender, sender2);

		when(senderDAO.findAll()).thenReturn(senders);

		// act
		List<Sender> actual = senderService.getAllSenders();

		// assert
		assertThat(actual.size()).isEqualTo(2);
		verify(senderDAO, times(1)).findAll();

	}
	
}
