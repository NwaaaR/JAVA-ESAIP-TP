package org.esaip.ema.ira2028;

import jakarta.persistence.*; // + simple, on import tout

import java.util.List;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour le AUTO_INCREMENT de MySQL
    private Integer id;

    @Column(name="TITRE")
    private String titres;

    @Column(name="AUTEUR")
    private String auteur;

    // Constructeur vide [OBLIGATOIRE]
    public Livre(){}

    public static void main(String[] arg){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // Step 1 - FIND avec ID
        Livre l1 = em.find(Livre.class,1);
        if(l1!=null){
            System.out.println("Livre trouvé : "+l1.getTitres() +"par :"+ l1.getAuteur());
        }

        // Step 2 - Insérer
        tx.begin();
        Livre nouveau= new Livre();
        nouveau.setTitres("Erwann et les 8nains");
        nouveau.setAuteur("Erwann (Obviously)");
        em.persist(nouveau);
        tx.commit();
        System.out.println("Livre inséré. Bravo clown.");

        // Modif
        tx.begin();
        Livre l5=em.find(Livre.class, 5);
        if(l5!=null){
            l5.setTitres("Je Suis Olivier Lavergne.");
            l5.setAuteur("Oulouvier de La Vergne");
            System.out.println("Livre d'ID : " + l5.getId() +"à été modif");
        }
        tx.commit();

        // Suppresion
        tx.begin();
        Livre aSuppr = em.find(Livre.class,4);
        if (aSuppr!=null){
            em.remove(aSuppr);
            System.out.println("Suppression effectué. Bravo.");
        }
        tx.commit();

        // -------------Utilisant les Query ----------------##
        // Requete JPQL par titres
        TypedQuery<Livre> QTitre = em.createQuery("SELECT lv FROM Livre lv WHERE lv.titres=:t",Livre.class);
        QTitre.setParameter("t","Guerre et paix");
        Livre livreTitre=QTitre.getSingleResult();
        System.out.println("Livre trouvé. Bravo. Tu as trouvé par titre: "+ livreTitre.getAuteur());

        // Requete JPQL par auteur
        TypedQuery<Livre> QAuteur= em.createQuery("SELECT lv FROM Livre lv WHERE lv.auteur=:a", Livre.class);
        QAuteur.setParameter("a","Leon Tolstoi");
        List<Livre> livresAuteur=QAuteur.getResultList();

        // Afficher TOUT les LIVRES
        TypedQuery<Livre> qAll = em.createQuery("SELECT l FROM Livre l", Livre.class);
        List<Livre> tousLesLivres = qAll.getResultList();
        for (Livre l : tousLesLivres) {
            System.out.println("ID: " + l.getId() + " | " + l.getTitres() + " - " + l.getAuteur());
        }

        em.close();
        emf.close();

    }

    public Integer getId() {
        return id;
    }
    public String getTitres() {
        return titres;
    }
    public String getAuteur() {
        return auteur;
    }

    public void setTitres(String titres) {
        this.titres = titres;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
}
