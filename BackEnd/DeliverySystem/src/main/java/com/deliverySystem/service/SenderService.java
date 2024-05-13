package com.deliverySystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverySystem.model.Sender;
import com.deliverySystem.repository.SenderDAO;

@Service
public class SenderService {

	@Autowired
	private SenderDAO senderDAO;

	public List<Sender> getAllSenders() {
		return senderDAO.findAll();
	}

	public Sender createSender(Sender sender) {
		if (!senderDAO.existsById(sender.getId())) {
			return senderDAO.save(sender);
		}
		return null;
	}

	public Sender findSenderByEmailAddress(String emailAddress) {
		return this.senderDAO.findSenderByEmailAddress(emailAddress);
	}

}
