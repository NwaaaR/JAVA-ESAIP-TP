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

        // --- LivretA ---
        LivretA livret = new LivretA();
        livret.setNumero("LivreAuNumero0001");
        livret.setSolde(1500.0);
        livret.setTaux(3.0);
        livret.setBanque(banque);
        em.persist(livret);

        // --- Client ---
        Adresse adrClient = new Adresse();
        adrClient.setNumero(12);
        adrClient.setRue("Rue de la Rue");
        adrClient.setCodePostal(6000049);
        adrClient.setVille("Rue.");

        Client client = new Client();
        client.setNom("Marchand");
        client.setPrenom("Erwann");
        client.setDateNaissance(LocalDate.of(1000, 1, 20));
        client.setAdresse(adrClient);
        client.setComptes(List.of(livret));
        em.persist(client);

        // --- Virement ---
        Virement virement = new Virement();
        virement.setDate(LocalDateTime.now());
        virement.setMontant(200.0);
        virement.setMotif("Loyer");
        virement.setBeneficiaire("Landlord SCI");
        virement.setCompte(livret);
        em.persist(virement);

        tx.commit();
        System.out.println("Done ggwp ff15 !");

        em.close();
        emf.close();
    }
}