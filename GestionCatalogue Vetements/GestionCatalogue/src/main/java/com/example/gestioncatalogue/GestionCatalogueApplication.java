package com.example.gestioncatalogue;

import com.example.gestioncatalogue.dao.CategoryRepository;
import com.example.gestioncatalogue.dao.ProduitRepository;
import com.example.gestioncatalogue.entities.Categorie;
import com.example.gestioncatalogue.entities.Produit;
import com.example.gestioncatalogue.security.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class GestionCatalogueApplication  {

    public static void main(String[] args) {
        SpringApplication.run(GestionCatalogueApplication.class, args);

    }



    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //@Bean
    CommandLineRunner commandLineRunner(IAccountService accountService)
    {
        return args -> {
            accountService.addRole("USER");
            accountService.addRole("ADMIN");
            accountService.addUser("user","123","user@gmail.com");
            accountService.addUser("admin","123","admin@gmail.com");
            accountService.addRoleToUser("user","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");


        };
    }
}
