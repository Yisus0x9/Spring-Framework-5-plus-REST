package org.yisus.spring.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yisus.spring.users.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    public Optional<User> findByEmail(String username);
    public Optional<Page<User>> findByNameContains(String contain, Pageable pageable);

    @Query("SELECT u.email FROM User u")
    public Page<String> findAllEmails(Pageable pageable);}
