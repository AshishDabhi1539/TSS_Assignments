package com.tss.service;

import java.util.List;
import java.util.Random;

import com.tss.model.DeliveryPartner;

public class DeliveryService {
    private List<DeliveryPartner> partners;

    public DeliveryService(List<DeliveryPartner> partners) {
        this.partners = partners;
    }

    /**
     * Sets the list of delivery partners.
     * @param partners The list of delivery partners.
     */
    public void setPartners(List<DeliveryPartner> partners) {
        this.partners = partners;
    }

    /**
     * Assigns a random delivery partner.
     * @return The assigned delivery partner or a default if none available.
     */
    public DeliveryPartner assignPartner() {
        if (partners == null || partners.isEmpty()) {
            return new DeliveryPartner(0, "No Delivery Partner Available");
        }
        return partners.get(new Random().nextInt(partners.size()));
    }
}