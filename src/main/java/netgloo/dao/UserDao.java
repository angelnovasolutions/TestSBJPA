package netgloo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import netgloo.models.*;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.Persistence
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class UserDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   
  public void create(User user) {
    entityManager.persist(user);
    return;
  }*/
  
  /**
   * Delete the user from the database.
   
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }*/
  
  /**
   * Return all the users stored in the database.
   
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return entityManager.createQuery("from User").getResultList();
  }*/
  
  /**
   * Return the user having the passed email.
   
  public User getByEmail(String email) {
    return (User) entityManager.createQuery(
        "from User where email = :email")
        .setParameter("email", email)
        .getSingleResult();
  }*/

  /**
   * Return the user having the passed id.
   
  public User getById(long id) {
    return entityManager.find(User.class, id);
  }*/

  /**
   * Update the passed user in the database.
   
  public void update(User user) {
    entityManager.merge(user);
    return;
  }*/
  
  //////////////////////////
  // ADB
  //////////////////////////
  
  
  /**
   * Return all the Formats stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<ADB_FileLine> getAllLinesByFile(String IDFileType) { 
	  try{
		  return entityManager.createQuery(	"SELECT fl FROM ADB_FileLine fl " +
				  							"JOIN fl.adbFileDefinition fd " +
				  							"WHERE fd.IDFileType = " + IDFileType.trim()).getResultList();  
	  } catch(Exception ex){
		  return null;
	  }	  
  }
  
  
  /**
   * Return all the Formats stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<ADB_FileField> getAllFieldsByFile(String IDFileType, String IDLineType) { 
	  try{
		  String line = "";
		  
		  if(!IDLineType.equals("")){
			  line = " and fl.IDLineType = " + IDLineType.trim();
		  }
		  
		  return entityManager.createQuery(	"SELECT ff FROM ADB_FileDefinition fd " +
				  							"JOIN fd.adbFileLines fl " +
				  							"JOIN fl.adbFileFields ff " +
				  							"WHERE fd.IDFileType = " + IDFileType.trim() + line).getResultList();  
	  } catch(Exception ex){
		  return null;
	  }	  
  }
  

  /**
   * Return all the Formats stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<PruebaPs> getPrueba() { 
	  try{		  
		  return entityManager.createQuery("from PruebaPs").getResultList();  
	  } catch(Exception ex){
		  return null;
	  }	  
  }
  
  /**
   * Invoke a Stored Procedure
   */
  public String getCallSP(){
	  
	  // Variable
	  String suma = "";
	  try{
		  // Create Call Stored Procedure
		  //entityManager.getTransaction().begin();
		  StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("sumaXY");
		  
		  // Set Parameters
		  storedProcedure.registerStoredProcedureParameter("x", Integer.class, ParameterMode.IN);
		  storedProcedure.registerStoredProcedureParameter("y", Integer.class, ParameterMode.IN);
		  storedProcedure.registerStoredProcedureParameter("suma", String.class, ParameterMode.OUT);
		  
		  storedProcedure.setParameter("x", 13);
		  storedProcedure.setParameter("y", 15);
		  
		  // execute SP
	      storedProcedure.execute();
	        
	      // get result
	      suma = (String)storedProcedure.getOutputParameterValue("suma");
	        System.out.println("La suma es: " + suma);
		  
	      //entityManager.getTransaction().commit();
	      //entityManager.close();
	        
		  
	  } catch(Exception ex){
		  System.out.println(ex.getMessage());
	  }
	  
	  return "La suma es: " + suma;
  }
  
 
  
  
  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class UserDao
