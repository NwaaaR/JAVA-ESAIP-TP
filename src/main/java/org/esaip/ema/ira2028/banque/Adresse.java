package org.esaip.ema.ira2028.banque;

import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
    private int numero;
    private String rue;
    private int codePostal;
    private String ville;

    public Adresse() {}

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }
    public int getCodePostal() { return codePostal; }
    public void setCodePostal(int codePostal) { this.codePostal = codePostal; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
}
