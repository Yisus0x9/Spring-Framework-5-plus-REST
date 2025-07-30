package org.yisus.spring.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.Address;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    public Page<Address> findByStreetContains(String contain, Pageable pageable);

    @Query("SELECT a.city FROM Address a GROUP BY a.city")
    public Page<String> findAllCities(Pageable pageable);

    @Query("SELECT a FROM Address a WHERE a.profile.user.id=?1")
    public Optional<Address> findByUserId(UUID userId);

    @Query("SELECT a FROM Address a WHERE a.profile.user.id=?1 AND a.profile.id=?2 AND a.id=?3")
    public Optional<Address> findByUserIdAndProfileIdAndAddressId(UUID userId,UUID profileId,UUID addressId);
}
