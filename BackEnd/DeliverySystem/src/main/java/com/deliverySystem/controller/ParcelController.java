package com.deliverySystem.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deliverySystem.model.Parcel;
import com.deliverySystem.service.ParcelService;

@RestController
@RequestMapping("/api/v1/parcel")
public class ParcelController {

	@Autowired
	private ParcelService parcelService;

	@GetMapping
	public ResponseEntity<?> getParcels() {
		return new ResponseEntity<>(parcelService.getAllParcels(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Parcel> createParcel(@RequestBody Parcel parcel) {
		Parcel createdParcel = parcelService.createParcel(parcel);

		if (createdParcel != null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{parcelId}")
					.buildAndExpand(createdParcel.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@GetMapping("/{trackingNumber}")
	public ResponseEntity<Parcel> getParcel(@PathVariable String trackingNumber) {
		Parcel parcel = parcelService.findParcelByTrackingNumber(trackingNumber);

		if (parcel != null) {
			return ResponseEntity.status(HttpStatus.OK).body(parcel);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParcel(@PathVariable Long id) {
		if (parcelService.removeParcel(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping
	public ResponseEntity<Parcel> updateParcel(@RequestBody Parcel parcel) {

		if (parcelService.updateParcel(parcel)) {
			return ResponseEntity.ok(parcel);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
