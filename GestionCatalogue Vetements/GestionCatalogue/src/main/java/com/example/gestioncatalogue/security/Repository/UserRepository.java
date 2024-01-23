package com.example.gestioncatalogue.security.Repository;

import com.example.gestioncatalogue.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
    public AppUser findAppUserByUsername(String userName);
}
