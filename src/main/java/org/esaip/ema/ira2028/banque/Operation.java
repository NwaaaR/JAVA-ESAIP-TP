package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE_OP")
    private LocalDateTime date;

    @Column(name = "MONTANT")
    private double montant;

    @Column(name = "MOTIF")
    private String motif;

    @ManyToOne
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;

    public Operation() {}

    public Integer getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }
    public Compte getCompte() { return compte; }
    public void setCompte(Compte compte) { this.compte = compte; }
}