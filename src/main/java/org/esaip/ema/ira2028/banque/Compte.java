package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMPTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "SOLDE")
    private double solde;

    @ManyToOne
    @JoinColumn(name = "ID_BANQUE")
    private Banque banque;

    @ManyToMany(mappedBy = "comptes")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Operation> operations = new ArrayList<>();

    public Compte() {}

    public Integer getId() { return id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }
    public Banque getBanque() { return banque; }
    public void setBanque(Banque banque) { this.banque = banque; }
    public List<Client> getClients() { return clients; }
    public List<Operation> getOperations() { return operations; }
}