package com.deliverySystem.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deliverySystem.model.Sender;
import com.deliverySystem.service.SenderService;

@RestController
@RequestMapping("/api/v1/sender")
public class SenderController {

	@Autowired
	private SenderService senderService;

	@GetMapping
	public ResponseEntity<?> getSenders() {
		return new ResponseEntity<>(senderService.getAllSenders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Sender> createSender(@RequestBody Sender sender) {
		Sender createdSender = senderService.createSender(sender);

		if (createdSender != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{senderId}")
					.buildAndExpand(createdSender.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@GetMapping("/{emailAddress}")
	public ResponseEntity<Sender> login(@PathVariable String emailAddress) {
		Sender sender = senderService.findSenderByEmailAddress(emailAddress);

		if (sender != null) {
			return ResponseEntity.status(HttpStatus.OK).body(sender);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
