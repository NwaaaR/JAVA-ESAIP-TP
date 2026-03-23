package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "BANQUE")
public class Banque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOM")
    private String nom;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "banque", cascade = CascadeType.ALL)
    private List<Compte> comptes;

    public Banque() {}

    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public List<Compte> getComptes() { return comptes; }
    public void setComptes(List<Compte> comptes) { this.comptes = comptes; }
}