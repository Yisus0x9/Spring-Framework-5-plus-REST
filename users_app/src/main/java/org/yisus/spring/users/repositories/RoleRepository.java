package org.yisus.spring.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    public Optional<Role> findByName(String name);
    public Optional<Page<Role>> findByNameContains(String contain, Pageable pageable);

    @Query("Select r.name FROM Role r")
    public Optional<Page<String>> findAllNames(Pageable pageable);
}
