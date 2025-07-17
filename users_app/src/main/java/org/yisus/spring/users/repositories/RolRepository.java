package org.yisus.spring.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.yisus.spring.users.entities.Role;

public interface RolRepository extends CrudRepository<Role, Long> {
}
