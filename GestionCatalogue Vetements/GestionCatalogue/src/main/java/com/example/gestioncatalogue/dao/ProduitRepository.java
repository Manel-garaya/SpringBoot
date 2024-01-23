package com.example.gestioncatalogue.dao;

import com.example.gestioncatalogue.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    public Page<Produit> findByReferenceContaining(String mc, Pageable p);
    @Query("select p from Produit p where p.categorie.id=:x")
    public List<Produit> rechercherProduitParCat(@Param("x") Long idC);

}
