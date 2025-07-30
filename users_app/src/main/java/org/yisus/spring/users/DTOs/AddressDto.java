package org.yisus.spring.users.DTOs;

import org.yisus.spring.users.entities.Address;

import java.io.Serializable;
import java.util.UUID;

public class AddressDto implements Serializable {
    private Address address;
    private UUID userId;
    private UUID profileId;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }
}
