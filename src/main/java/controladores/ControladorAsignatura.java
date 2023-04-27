package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import examen270423JPA.Asignatura;
import examen270423JPA.Asignaturaspordocente;

public class ControladorAsignatura {
	
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("examen270423");
	
	
	/**
	 * 
	 * @return
	 */
	public static List<Asignatura> findAll() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM asignatura;", Asignatura.class);
        List<Asignatura> l = (List<Asignatura>) q.getResultList();

        em.close();
        return l;
    }

}
