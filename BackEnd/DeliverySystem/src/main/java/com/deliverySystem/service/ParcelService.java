package com.deliverySystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverySystem.model.Parcel;
import com.deliverySystem.repository.ParcelDAO;

@Service
public class ParcelService {

	@Autowired
	private ParcelDAO parcelDAO;

	public List<Parcel> getAllParcels() {
		return parcelDAO.findAll();
	}

	public Parcel createParcel(Parcel parcel) {
		if (!parcelDAO.existsById(parcel.getId())) {
			return parcelDAO.save(parcel);
		}
		return null;
	}

	public Parcel findParcelByTrackingNumber(String trackingNumber) {
		return this.parcelDAO.findParcelByTrackingNumber(trackingNumber);
	}

	public boolean removeParcel(Long id) {
		if (parcelDAO.existsById(id)) {
			parcelDAO.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean updateParcel(Parcel parcel) {
		if (parcelDAO.existsById(parcel.getId())) {
			parcelDAO.save(parcel);
			return true;
		}
		return false;
	}
}
