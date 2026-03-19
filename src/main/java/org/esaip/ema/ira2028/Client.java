package org.esaip.ema.ira2028;

import jakarta.persistence.*; // + simple, on import tout

import java.util.List;
import java.util.Set;
@Entity
@Table(name = "CLIENT")
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

    public Client(){}
    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public Set<Emprunt> getEmprunts() { return emprunts; }
}
