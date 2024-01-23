package com.example.gestioncatalogue.service;

import com.example.gestioncatalogue.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceProduit {
    public void saveProduit(Produit p, MultipartFile mf) throws IOException;
    public List<Produit> getAllProdcuts();
    public List<Produit> getProductsBCat(Long idCat);
    public void deleteProduct(Long id);
    public Produit getProdcut(Long id);
    public Page<Produit> getProductByMC(String mc, Pageable p);
    public byte[] getImage(Long id) throws IOException;

    void addToFavorites(Long id,String user);
    public void removeFavourite(String userId, Long produitId);
}
