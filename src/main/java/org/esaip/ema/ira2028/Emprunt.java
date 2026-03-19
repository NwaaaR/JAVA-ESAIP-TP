package org.esaip.ema.ira2028;

import jakarta.persistence.*; // + simple, on import tout

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour le AUTO_INCREMENT de MySQL
    private Integer id;

    @Column(name="DATE_DEBUT")
    private LocalDateTime dateDebut;

    @Column(name="DATE_FIN")
    private LocalDateTime dateFin;

    @Column(name="DELAI")
    private Integer delai;

    @JoinColumn(name="ID_CLIENT")
    @ManyToOne
    private Client client;

    @ManyToMany
    @JoinTable(
            name="COMPO",
            joinColumns = @JoinColumn(name="ID_EMP",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name="ID_LIV",referencedColumnName = "ID")
    )
    private Set<Livre> livres;

    public Emprunt() {}

    public Integer getId() { return id; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public Integer getDelai() { return delai; }
    public Client getClient() { return client; }
    public Set<Livre> getLivres() { return livres; }
}
