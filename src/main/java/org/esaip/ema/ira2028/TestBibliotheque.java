package org.esaip.ema.ira2028;

import jakarta.persistence.*;
import java.util.List;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Requête 1 : extraire un emprunt (ID=5)
        Emprunt emprunt = em.find(Emprunt.class, 5);
        if (emprunt != null) {
            System.out.println("Emprunt ID : " + emprunt.getId());
            for (Livre l : emprunt.getLivres()) {
                System.out.println("Livre : " + l.getTitres() + " par " + l.getAuteur());
            }
        }

        // Requête 2 : tous les emprunts d'un client donné (ID=1)
        TypedQuery<Emprunt> query = em.createQuery(
                "SELECT e FROM Emprunt e WHERE e.client.id = :idClient", Emprunt.class
        );
        query.setParameter("idClient", 1);
        List<Emprunt> emprunts = query.getResultList();

        System.out.println("Emprunts du client ID 1 :");
        for (Emprunt e : emprunts) {
            System.out.println("Emprunt ID : " + e.getId() +
                    "Début : " + e.getDateDebut());
        }

        em.close();
        emf.close();
    }
}
