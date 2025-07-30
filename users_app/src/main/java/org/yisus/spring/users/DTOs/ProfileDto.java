package org.yisus.spring.users.DTOs;

import org.yisus.spring.users.entities.Profile;

import java.io.Serializable;
import java.util.UUID;

public class ProfileDto implements Serializable {
    private Profile profile;
    private UUID userId;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
