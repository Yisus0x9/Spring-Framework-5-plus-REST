package org.yisus.spring.users.repositories;

import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.Profile;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    public Optional<Page<Profile>> findByNicknameContains(String contain, Pageable pageable);

    @Query("SELECT p FROM Profile p WHERE p.user.id=?1")
    public Optional<Profile> findByUserId(UUID userId);

    @Query("Select p FROM Profile p WHERE p.user.id=?1 AND p.id=?2")
    public Optional<Profile> findByUserIdAndProfileId( UUID userId,UUID profileId);
}
