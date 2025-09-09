package com.EcommerceWeb.repositories;

import com.EcommerceWeb.model.AppRole;
import com.EcommerceWeb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}
