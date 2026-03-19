package org.esaip.ema.ira2028;

import jakarta.persistence.*; // + simple, on import tout

import java.util.List;
import java.util.Set;
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour le AUTO_INCREMENT de MySQL
    private Integer id;

    @Column(name="NOM")
    private String nom;

    @Column(name="PRENOM")
    private String prenom;

    @OneToMany(mappedBy = "client")
    private Set<Emprunt> emprunts; // Ref vers les emprunts de class emprunt
}
