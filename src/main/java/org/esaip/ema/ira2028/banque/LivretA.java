package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;

@Entity
@Table(name = "LIVRET_A")
public class LivretA extends Compte {
    @Column(name = "TAUX")
    private double taux;

    public LivretA() {}

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
}