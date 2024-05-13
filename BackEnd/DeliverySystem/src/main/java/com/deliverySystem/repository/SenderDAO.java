package com.deliverySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverySystem.model.Sender;

public interface SenderDAO extends JpaRepository<Sender, Long> {

	Sender findSenderByEmailAddress(String emailAddress);

}
