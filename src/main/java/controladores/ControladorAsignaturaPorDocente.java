package controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import examen270423JPA.Asignatura;
import examen270423JPA.Asignaturaspordocente;
import examen270423JPA.Docente;

public class ControladorAsignaturaPorDocente {
	
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("examen270423");
	
	/**
	 * 
	 * @return
	 */
	public static List<Asignaturaspordocente> findAll() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente;", Asignaturaspordocente.class);
        List<Asignaturaspordocente> l = (List<Asignaturaspordocente>) q.getResultList();

        em.close();
        return l;
    }
	
	/**
	 * 
	 */
	public static List<Asignaturaspordocente> findIsSelect(int i) {

		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente WHERE idDocente LIKE ?", Asignaturaspordocente.class);

		
		
		
		q.setParameter(1, "%" + i + "%");
		List<Asignaturaspordocente> lista = (List<Asignaturaspordocente>) q.getResultList();

		em.close();
		return lista;
	}
	
	/**
	 * 
	 */
	public static List<Asignaturaspordocente> findIsNotSelect(int i) {

		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM asignaturaspordocente WHERE idDocente NOT LIKE ?", Asignaturaspordocente.class);
		
		q.setParameter(1, "%" + i + "%");
		List<Asignaturaspordocente> lista = (List<Asignaturaspordocente>) q.getResultList();

		em.close();
		return lista;
	}
	
	/**
	 * 
	 * @param l
	 */
	public static void insertar (Asignaturaspordocente l) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(l);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * 
	 * @param l
	 */
	public static void modificar (Asignaturaspordocente l) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(l);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * 
	 * @param d
	 * @param a
	 * @return
	 */
	public static Asignaturaspordocente docenteAsignatura(Docente d, Asignatura a) {
		Asignaturaspordocente v = null;
	    EntityManager em = entityManagerFactory.createEntityManager();
	    try {
	        Query q = em.createNativeQuery("select * from asignaturaspordocente where idDocente = "+d.getId()+" and idAsignatura = "+a.getId()+";", Asignaturaspordocente.class);
	        v = (Asignaturaspordocente) q.getSingleResult();
	    } catch (Exception e2) {
	    }
	    em.close();
	    return v;
	}

}
