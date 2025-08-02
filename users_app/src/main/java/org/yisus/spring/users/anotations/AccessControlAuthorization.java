package org.yisus.spring.users.anotations;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
@PostAuthorize("returnObject.id != null and hasRole('ROLE_SUPER_ADMIN')")
public @interface AccessControlAuthorization {
}
