package com.tss.repository;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.DeliveryPartner;
import com.tss.util.SerializationUtil;

public class PartnerRepository {
	private List<DeliveryPartner> partners;
	private static final String FILE_NAME = "partners.ser";

	public PartnerRepository() {
		partners = SerializationUtil.readObject(FILE_NAME);
		if (partners == null) {
			partners = new ArrayList<>();
		}
	}

	public void add(DeliveryPartner partner) {
		partners.add(partner);
		save();
	}

	public void update(DeliveryPartner updatedPartner) {
		for (int i = 0; i < partners.size(); i++) {
			if (partners.get(i).getId() == updatedPartner.getId()) {
				partners.set(i, updatedPartner);
				save();
				return;
			}
		}
	}

	public boolean remove(int id) {
		for (int i = 0; i < partners.size(); i++) {
			if (partners.get(i).getId() == id) {
				partners.remove(i);
				reassignPartnerIds();
				save();
				return true;
			}
		}
		return false;
	}
	
	private void reassignPartnerIds() {
	    for (int i = 0; i < partners.size(); i++) {
	        partners.get(i).setId(i + 1);
	    }
	}

	public List<DeliveryPartner> getAll() {
		return partners;
	}

	public void save() {
		SerializationUtil.saveObject(partners, FILE_NAME);
	}
}