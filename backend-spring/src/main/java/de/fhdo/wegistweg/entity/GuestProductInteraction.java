package de.fhdo.wegistweg.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("GUEST")
public class GuestProductInteraction extends ProductInteraction {
    private String guestSessionId;

    public GuestProductInteraction() {
    }

    public GuestProductInteraction(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }

    public GuestProductInteraction(Product product, LocalDateTime timestamp, ProductInteractionType interactionType, String guestSessionId) {
        super(product, timestamp, interactionType);
        this.guestSessionId = guestSessionId;
    }

    @Override
    public String getActorIdentifier() {
        return guestSessionId;
    }

    public String getGuestSessionId() {
        return guestSessionId;
    }

    public void setGuestSessionId(String guestSessionId) {
        this.guestSessionId = guestSessionId;
    }
}
