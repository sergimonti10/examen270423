package controladores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import examen270423JPA.Docente;

public class ControladorDocente {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("examen270423");
	
	
	
	
	/**
	 * 
	 */
	public static List<Docente> findByDescription(String descripcion) {

		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM docente WHERE nombreCompleto LIKE ?", Docente.class);

		
		
		
		q.setParameter(1, "%" + descripcion + "%");
		List<Docente> lista = (List<Docente>) q.getResultList();

		em.close();
		return lista;
	}
	
}
