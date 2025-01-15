package org.example.example2Relacion1_muchosBidireccional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Configuramos el EMF a través de la unidad de persistencia
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiUnidadDePersistencia");

        //Generamos un EntityManager
        EntityManager em = emf.createEntityManager();

        //Iniciamos una transacción
        em.getTransaction().begin();

        // Construimos un objeto de tipo User
        User user1 = new User();
        user1.setUserName("Pepe");
        user1.setUserMessage("Hello world from JPA with Pepe");

        // Construimos otro objeto de tipo User
        User user2 = new User();
        user2.setUserName("Juan");
        user2.setUserMessage("Hello world from JPA with Juan");

        Phone phone1 = new Phone();
        phone1.setNumber("123456789");
        user1.addPhone(phone1);

        //Persistimos los objetos
        em.persist(user1);
        em.persist(user2);

//        em.persist(phone1);

        //Commiteamos la transacción
        em.getTransaction().commit();

        //Cerramos el EntityManager
        em.close();
    }
}