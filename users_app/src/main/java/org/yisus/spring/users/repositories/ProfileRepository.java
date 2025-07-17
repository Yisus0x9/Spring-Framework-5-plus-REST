package org.yisus.spring.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yisus.spring.users.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
