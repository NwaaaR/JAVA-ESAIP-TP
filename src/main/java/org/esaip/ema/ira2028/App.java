package org.esaip.ema.ira2028;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        System.out.println("Connexion : " +em );
        System.out.println(emf);
        em.close();
        emf.close();
    }
}
