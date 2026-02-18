package org.esaip.ema.ira2028;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    static void main() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        System.out.println(emf);
        emf.close();
    }
}
