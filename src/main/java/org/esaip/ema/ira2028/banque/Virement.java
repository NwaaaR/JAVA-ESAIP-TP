package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;

@Entity
@Table(name = "VIREMENT")
public class Virement extends Operation {
    @Column(name = "BENEFICIAIRE")
    private String beneficiaire;

    public Virement() {}

    public String getBeneficiaire() { return beneficiaire; }
    public void setBeneficiaire(String b) { this.beneficiaire = b; }
}