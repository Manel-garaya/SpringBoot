package com.example.gestioncatalogue.security.Repository;

import com.example.gestioncatalogue.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,String> {
}
