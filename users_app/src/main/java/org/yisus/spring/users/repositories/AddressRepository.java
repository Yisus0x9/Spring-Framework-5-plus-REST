package org.yisus.spring.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yisus.spring.users.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
