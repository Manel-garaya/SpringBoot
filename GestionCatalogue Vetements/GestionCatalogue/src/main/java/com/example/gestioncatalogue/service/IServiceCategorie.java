package com.example.gestioncatalogue.service;

import com.example.gestioncatalogue.entities.Categorie;
import com.example.gestioncatalogue.entities.Produit;

import java.util.List;

public interface IServiceCategorie {
    public void saveCategorie(Categorie c);
    public List<Categorie> getAllCategories();
    public void deleteCategory(Long id);
    public Categorie getCategory(Long id);
}
