package com.tss.repository;

import java.util.ArrayList;
import java.util.List;

import com.tss.model.DeliveryPartner;
import com.tss.util.SerializationUtil;

public class PartnerRepository {
    private static final String PARTNER_FILE = "partners.ser";
    private List<DeliveryPartner> partners;

    public PartnerRepository() {
        this.partners = SerializationUtil.readList(PARTNER_FILE);
        if (partners == null) {
            partners = new ArrayList<>();
        }
    }

    /**
     * Gets all delivery partners.
     * @return List of delivery partners.
     */
    public List<DeliveryPartner> getAll() {
        return partners;
    }

    /**
     * Adds a delivery partner to the repository.
     * @param partner The delivery partner to add.
     */
    public void add(DeliveryPartner partner) {
        partners.add(partner);
        save();
    }

    /**
     * Updates a delivery partner in the repository.
     * @param partner The updated delivery partner.
     */
    public void update(DeliveryPartner partner) {
        partners.removeIf(p -> p.getId() == partner.getId());
        partners.add(partner);
        save();
    }

    /**
     * Removes a delivery partner from the repository.
     * @param id The ID of the delivery partner.
     * @return True if removed, false otherwise.
     */
    public boolean remove(int id) {
        boolean removed = partners.removeIf(p -> p.getId() == id);
        if (removed) {
            save();
        }
        return removed;
    }

    /**
     * Saves the partners to persistent storage.
     */
    public void save() {
        SerializationUtil.saveList(partners, PARTNER_FILE);
    }
}