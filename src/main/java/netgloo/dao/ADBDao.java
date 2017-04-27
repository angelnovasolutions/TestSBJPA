package netgloo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import netgloo.dao.*;
import netgloo.models.*;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class ADBDao {

	// ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;

		
	  /**
		* Return all the users stored in the database.
	  */
	  @SuppressWarnings("unchecked")
	  public List<ADB_FileLine> getAlladb() { 
		return entityManager.createQuery("from ADB_FileLine").getResultList();
	  }
}
