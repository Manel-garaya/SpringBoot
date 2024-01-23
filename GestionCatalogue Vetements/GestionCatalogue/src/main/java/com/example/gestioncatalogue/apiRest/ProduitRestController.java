package com.example.gestioncatalogue.apiRest;

import com.example.gestioncatalogue.entities.Produit;
import com.example.gestioncatalogue.service.IServiceProduit;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class ProduitRestController {

    IServiceProduit serviceProduit;
    @GetMapping("/all")
    public List<Produit> getAllProducts(
                                 @RequestParam(name = "mc",defaultValue = "") String mc,
                                 @RequestParam(name = "page", defaultValue = "0")int page,
                                 @RequestParam(name = "size", defaultValue = "4")int size){
        Page<Produit> liste=serviceProduit.getProductByMC(mc, PageRequest.of(page,size));
         return liste.getContent();


    }
    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("id") Long productId) {

            serviceProduit.deleteProduct(productId);

    }
     @PostMapping("/add")
    public void saveProduct(@RequestParam("produit") String p, @RequestParam("file") MultipartFile mf) throws IOException {
    Produit produit= new ObjectMapper().readValue(p,Produit.class);
    serviceProduit.saveProduit(produit,mf);


     }
     @GetMapping(path="/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException{
        return serviceProduit.getImage(id);
     }

}
