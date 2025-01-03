package de.fhdo.wegistweg.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("REGISTERED")
public class RegisteredUserProductInteraction extends ProductInteraction {
    @ManyToOne
    User user;


    public RegisteredUserProductInteraction() {
    }

    public RegisteredUserProductInteraction(Product product, LocalDateTime timestamp, ProductInteractionType interactionType, User user) {
        super(product, timestamp, interactionType);
        this.user = user;
    }


    @Override
    public String getActorIdentifier() {
        return user != null ? String.valueOf(user.getId()) : null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
