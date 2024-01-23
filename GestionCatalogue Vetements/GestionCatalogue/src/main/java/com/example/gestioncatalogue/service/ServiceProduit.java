package com.example.gestioncatalogue.service;

import com.example.gestioncatalogue.dao.ProduitRepository;
import com.example.gestioncatalogue.entities.Produit;
import com.example.gestioncatalogue.security.Repository.UserRepository;
import com.example.gestioncatalogue.security.entities.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceProduit implements IServiceProduit{

private EntityManager entityManager;
    private ProduitRepository produitRepository;
    private UserRepository userRepository;
    @Override
    public void saveProduit(Produit p, MultipartFile mf) throws IOException {
        if(!mf.isEmpty()){
            p.setPhoto(saveImage(mf));
        }
        produitRepository.save(p);

    }
    @Override
    public List<Produit> getAllProdcuts() {
        return produitRepository.findAll();
    }

    @Override
    public List<Produit> getProductsBCat(Long idCat) {
        return produitRepository.rechercherProduitParCat(idCat);
    }
    @Override
    public void deleteProduct(Long id) {
        produitRepository.deleteById(id);
    }
    @Override
    public Produit getProdcut(Long id) {
        return produitRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Produit> getProductByMC(String mc, Pageable p) {
        return produitRepository.findByReferenceContaining(mc,p);
    }

    @Override
    public byte[] getImage(Long id) throws IOException{
        File f=new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p =Paths.get(chemin,getProdcut(id).getPhoto());
        return Files.readAllBytes(p);
    }

    @Override
    public void addToFavorites(Long id,String user) {
        Produit p=produitRepository.findById(id).orElse(null);
        p.getFavourites().add(userRepository.findAppUserByUsername(user));
        System.out.println(p.getFavourites().get(0).getId());
        produitRepository.save(p);
    }
@Transactional
    @Override
    public void removeFavourite(String userId, Long produitId) {
    AppUser appUser = entityManager.find(AppUser.class, userId);
    Produit produit = entityManager.find(Produit.class, produitId);

    // Remove the Produit from the AppUser's favourites
    appUser.getMyfavourites().remove(produit);

    // Remove the AppUser from the Produit's favourites
    produit.getFavourites().remove(appUser);

    // Update the database
    entityManager.merge(appUser);
    entityManager.merge(produit);
    }

    private String saveImage(MultipartFile mf) throws IOException {
        String nomPhoto=mf.getOriginalFilename();
        String tab[]=nomPhoto.split("\\.");
        String newName=tab[0]+System.currentTimeMillis()+"."+tab[1];
        File filename=new ClassPathResource("static/photos").getFile();
        String chemin=filename.getAbsolutePath();
        Path p= Paths.get(chemin,newName);
        Files.write(p,mf.getBytes());

        return newName;
    }
}
