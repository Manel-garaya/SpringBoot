package com.example.gestioncatalogue.entities;

import com.example.gestioncatalogue.security.entities.AppUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Produit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @NotEmpty
    private String reference;
    @Min(1)
    private double prix;
    @Min(1)
    private int quantite;
    @ManyToOne
    @NotNull(message = "Not Null")
    private Categorie categorie;
    private String photo;
    @Column(name = "favourites_id")
    @ManyToMany(fetch = FetchType.LAZY)
    List<AppUser> favourites ;

    public void setReference(String reference) {
        this.reference = reference;
    }
}
