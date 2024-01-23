package com.example.gestioncatalogue.service;

import com.example.gestioncatalogue.dao.CategoryRepository;
import com.example.gestioncatalogue.entities.Categorie;

import java.util.List;

public class ServiceCategorie implements IServiceCategorie{
    private CategoryRepository categoryRepository;
    @Override
    public void saveCategorie(Categorie c) {

    }

    @Override
    public List<Categorie> getAllCategories() {
        return categoryRepository.findAll() ;
    }

    @Override
    public void deleteCategory(Long id) {

    }

    @Override
    public Categorie getCategory(Long id) {
        return null;
    }
}
