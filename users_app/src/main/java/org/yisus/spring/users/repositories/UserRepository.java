package org.yisus.spring.users.repositories;

import org.springframework.data.repository.CrudRepository;
import org.yisus.spring.users.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
