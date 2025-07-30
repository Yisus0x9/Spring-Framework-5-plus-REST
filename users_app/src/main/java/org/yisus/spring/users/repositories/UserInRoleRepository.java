package org.yisus.spring.users.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yisus.spring.users.entities.Role;
import org.yisus.spring.users.entities.User;
import org.yisus.spring.users.entities.UserInRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInRoleRepository extends JpaRepository<UserInRole, UUID> {

    @Query("SELECT uir FROM UserInRole uir WHERE uir.role.name IN :roleNames")
    public Page<UserInRole> findAllByRoles(@Param("roleNames")List<String> roles, Pageable pageable);

    @Query("SELECT uir.role FROM UserInRole uir WHERE uir.user.id=?1")
    public Page<Role> findRolesByUserId(UUID userId, Pageable pageable);

    @Query("SELECT uir FROM UserInRole uir WHERE uir.user.id=?1 AND uir.role.id=?2")
    public Optional<UserInRole> findByUserIdAndRoleId(UUID userId,UUID roleId);
}
