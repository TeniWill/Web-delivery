package com.deliverySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverySystem.model.Parcel;

public interface ParcelDAO extends JpaRepository<Parcel, Long> {

	Parcel findParcelByTrackingNumber(String trackingNumber);

}
