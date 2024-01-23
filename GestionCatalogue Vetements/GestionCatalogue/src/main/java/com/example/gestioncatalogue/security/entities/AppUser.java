package com.example.gestioncatalogue.security.entities;

import com.example.gestioncatalogue.entities.Produit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AppUser {
    @Id
    @Column(unique = true)
    private String id;
    private String username;
    private String passsword;
    private String mail;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;

    @ManyToMany(mappedBy = "favourites",fetch = FetchType.LAZY)
    List<Produit> myfavourites ;
}
