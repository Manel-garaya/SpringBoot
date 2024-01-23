package com.example.gestioncatalogue.controller;

import com.example.gestioncatalogue.dao.CategoryRepository;
import com.example.gestioncatalogue.entities.Produit;
import com.example.gestioncatalogue.security.Repository.UserRepository;
import com.example.gestioncatalogue.service.IServiceProduit;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProduitController {
    private IServiceProduit serviceProduit;
    private CategoryRepository categoryRepository;
    UserRepository userRepository;



    @GetMapping("/user/add-to-favorites/{id}")
    public String addToFavorites(@PathVariable("id") Long id, Authentication auth) {
        // Retrieve the product by ID


        // Create a new MyProdList entity and populate it with the product information
        //MyProdList myProdList = new MyProdList();
       // myProdList.setReference(product.getReference());
       // myProdList.setPrix(product.getPrix());
       // myProdList.setCategorie(product.getCategorie());
       // myProdList.setPhoto(product.getPhoto());

        // Save the MyProdList entity to add it to the favorites
        // You need to have a method in your service or repository to handle this
        // The example assumes you have a method like addToFavorites in your service
        System.out.println(auth.getName());
        serviceProduit.addToFavorites(id,auth.getName());

        return "redirect:/my_products";

    }

    @GetMapping("/user/remove-from-favorites/{id}")
    public String removefromfav(@PathVariable("id") Long id, Authentication auth) {
        // Retrieve the product by ID


        // Create a new MyProdList entity and populate it with the product information
        //MyProdList myProdList = new MyProdList();
        // myProdList.setReference(product.getReference());
        // myProdList.setPrix(product.getPrix());
        // myProdList.setCategorie(product.getCategorie());
        // myProdList.setPhoto(product.getPhoto());

        // Save the MyProdList entity to add it to the favorites
        // You need to have a method in your service or repository to handle this
        // The example assumes you have a method like addToFavorites in your service
        System.out.println(auth.getName());
        serviceProduit.removeFavourite(userRepository.findAppUserByUsername(auth.getName()).getId(),id);

        return "redirect:/my_products";
    }


    @GetMapping("/user/index")
    public String getAllProducts(Model m,
                                 @RequestParam(name = "mc",defaultValue = "") String mc,
                                 @RequestParam(name = "page", defaultValue = "0")int page,
                                 @RequestParam(name = "size", defaultValue = "5")int size)
    {
        Page<Produit> list=serviceProduit.getProductByMC(mc, PageRequest.of(page,size));
        m.addAttribute("mc",mc);
        m.addAttribute("products",list.getContent());
        m.addAttribute("currentpage",page);
        m.addAttribute("pages",new int[list.getTotalPages()]);



        return "prodList";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long idProduit){
        serviceProduit.deleteProduct(idProduit);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/formajout")
    public String form(Model m){
        m.addAttribute("categories", categoryRepository.findAll());
        m.addAttribute("produit",new Produit());
        return "formulaire";
    }
    @PostMapping("/admin/save")
    public String saveProduct(@Valid @ModelAttribute Produit p, BindingResult bindingResult, Model m, MultipartFile mb,@RequestParam("file") MultipartFile mf) throws IOException {
        if (bindingResult.hasErrors()){
            m.addAttribute("categories",categoryRepository.findAll());
            return "formulaire";
        }
        serviceProduit.saveProduit(p,mf);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") Long idP,Model m)
    {
        m.addAttribute("produit",serviceProduit.getProdcut(idP));
        m.addAttribute("categories",categoryRepository.findAll());
        return "formulaire";
    }

    @GetMapping("/my_products")
    public String getMyProducts(Authentication auth,Model m){
        m.addAttribute("products",userRepository.findAppUserByUsername(auth.getName()).getMyfavourites());

        return "myFavoris";
    }
    @RequestMapping("/mylist/{id}")
    public String getMylist(@PathVariable("id")Long id){
        return "";
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }
}


