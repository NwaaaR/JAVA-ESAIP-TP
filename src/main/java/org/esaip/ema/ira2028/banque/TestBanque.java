package org.esaip.ema.ira2028.banque;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestBanque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // --- Banque ---
        Adresse adrBanque = new Adresse();
        adrBanque.setNumero(1);
        adrBanque.setRue("Rue de la Rue Bis");
        adrBanque.setCodePostal(1000010101);
        adrBanque.setVille("Rue2.0");

        Banque banque = new Banque();
        banque.setNom("Banque D'Erwann");
        banque.setAdresse(adrBanque);
        em.persist(banque);

        // --- LivretA (existant) ---
        LivretA livret = new LivretA();
        livret.setNumero("LivreAuNumero0001");
        livret.setSolde(1500.0);
        livret.setTaux(3.0);
        livret.setBanque(banque);
        em.persist(livret);

        // --- NOUVEAU : Compte partagé entre 2 clients ---
        LivretA comptePartage = new LivretA();
        comptePartage.setNumero("LA-PARTAGE-001");
        comptePartage.setSolde(5000.0);
        comptePartage.setTaux(2.5);
        comptePartage.setBanque(banque);
        em.persist(comptePartage);

        // --- Client existant (maintenant lié à livret + comptePartage) ---
        Adresse adrClient = new Adresse();
        adrClient.setNumero(12);
        adrClient.setRue("Rue de la Rue");
        adrClient.setCodePostal(6000049);
        adrClient.setVille("Rue.");

        Client client = new Client();
        client.setNom("Erwann de ");
        client.setPrenom("Erwann");
        client.setDateNaissance(LocalDate.of(1000, 1, 20));
        client.setAdresse(adrClient);
        client.setComptes(List.of(livret, comptePartage)); // MODIFIÉ : ajout de comptePartage
        em.persist(client);

        // --- NOUVEAU : 2ème client lié au même comptePartage ---
        Client client2 = new Client();
        client2.setNom("Goubain-Thukuna");
        client2.setPrenom("Michel de chez Mazoyer");
        client2.setDateNaissance(LocalDate.of(1995, 6, 15));
        client2.setComptes(List.of(comptePartage)); // même compte que client
        em.persist(client2);

        // --- NOUVEAU : AssuranceVie pour client3 ---
        AssuranceVie assurance = new AssuranceVie();
        assurance.setNumero("AssuranceVitality-Gotaga");
        assurance.setSolde(10000.0);
        assurance.setTaux(4.5);
        assurance.setDateFin(LocalDate.of(2035, 12, 31));
        assurance.setBanque(banque);
        em.persist(assurance);

        // --- NOUVEAU : client3 avec LivretA + AssuranceVie ---
        Client client3 = new Client();
        client3.setNom("Clouvisse");
        client3.setPrenom("Ragnar de chez la-bas tôt");
        client3.setDateNaissance(LocalDate.of(1002, 3, 10));
        client3.setComptes(List.of(livret, assurance)); // 1 LivretA + 1 AssuranceVie
        em.persist(client3);

        // --- Virement existant ---
        Virement virement = new Virement();
        virement.setDate(LocalDateTime.now());
        virement.setMontant(2020.0);
        virement.setMotif("Loyer de chez Gabin");
        virement.setBeneficiaire("Erwann (Déguisé en Gabin)");
        virement.setCompte(livret);
        em.persist(virement);

        // --- NOUVEAU : Operation basique (non Virement) ---
        Operation op = new Operation();
        op.setDate(LocalDateTime.now());
        op.setMontant(2407.0);
        op.setMotif("Frais pour le VIETNAM LOL LA BLAGUE");
        op.setCompte(livret);
        em.persist(op);

        tx.commit();

        // Test pour TP5

        // 1. Clients du compte partagé
        System.out.println("\nClients du compte partagé");
        Compte compteRecup = em.find(Compte.class, comptePartage.getId());
        for (Client c : compteRecup.getClients()) {
            System.out.println(c.getPrenom() + " " + c.getNom());
        }

        // 2. Comptes de client3
        System.out.println("\nComptes de " + client3.getPrenom() + " " + client3.getNom());
        Client clientRecup = em.find(Client.class, client3.getId());
        for (Compte c : clientRecup.getComptes()) {
            if (c instanceof LivretA la) {
                System.out.println("LivretA : " + la.getNumero() + " Solde: " + la.getSolde() + " Taux: " + la.getTaux() + "%");
            } else if (c instanceof AssuranceVie av) {
                System.out.println("AssuranceVie : " + av.getNumero() + " Solde: " + av.getSolde() + " Taux: " + av.getTaux() + "% Date fin: " + av.getDateFin());
            }
        }

        System.out.println("\nDone ggwp ff15 !");
        em.close();
        emf.close();
    }
}