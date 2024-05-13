package com.deliverySystem.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "First name is required!")
	private String firstName;

	@NotBlank(message = "Last name is required!")
	private String lastName;

	@NotBlank(message = "Address is required!")
	private String address;

	private String trackingNumber;
	private LocalDate sendDate;
	private LocalDate deliveryDate;
	private String currentStatus;

	@PrePersist
	private void preInsert() {
		this.trackingNumber = UUID.randomUUID().toString();
		this.sendDate = LocalDate.now();
		this.deliveryDate = LocalDate.now().plusDays(7);
		this.currentStatus = "In Storage";
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getSendDate() {
		return sendDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
