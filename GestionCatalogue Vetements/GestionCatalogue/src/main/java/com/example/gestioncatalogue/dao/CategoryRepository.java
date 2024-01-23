package com.example.gestioncatalogue.dao;

import com.example.gestioncatalogue.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categorie,Long> {
}
