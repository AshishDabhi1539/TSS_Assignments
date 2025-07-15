package com.tss.service;

import java.util.List;
import java.util.Random;

import com.tss.model.DeliveryPartner;

public class DeliveryService {
	private List<DeliveryPartner> partners;

	public DeliveryService(List<DeliveryPartner> partners) {
		this.partners = partners;
	}

	public DeliveryPartner assignPartner() {
		if (partners == null || partners.isEmpty()) {
			return new DeliveryPartner(0, "No Delivery Partner Available");
		}
		return partners.get(new Random().nextInt(partners.size()));
	}
}
