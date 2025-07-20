package org.yisus.spring.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.Address;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
