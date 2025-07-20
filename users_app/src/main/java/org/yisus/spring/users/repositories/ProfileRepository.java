package org.yisus.spring.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.Profile;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
