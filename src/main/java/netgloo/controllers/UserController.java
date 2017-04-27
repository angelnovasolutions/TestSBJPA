package netgloo.controllers;

import netgloo.beans.FileCountFields;
import netgloo.models.*;
import netgloo.dao.*;
import netgloo.utils.TextReader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

/**
 * Class UserController
 */

@RestController
public class UserController {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;
  private ADBDao adbDao;
  
  
	
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
	  
	  /**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	@RequestMapping(value="/getAllLinesByFile")
	  @ResponseBody
	  public String getAllLinesByFile() {
		  
		  List<ADB_FileLine> lstUser = new ArrayList<ADB_FileLine>();
		  
	    try {
	    	lstUser = userDao.getAllLinesByFile("1");
	    	System.out.println("HOLA!! : " + lstUser.size());
	    }
	    catch (Exception ex) {
	      
	    }
	    return "Exitoso!!";
	  }
	
	
	/**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	@RequestMapping(value="/getAllFieldsByFile")
	  @ResponseBody
	  public String getAllFieldsByFile() {
		  
		  List<ADB_FileField> lstUser = new ArrayList<ADB_FileField>();
		  
	    try {
	    	lstUser = userDao.getAllFieldsByFile("1","1");
	    	System.out.println("HOLA!! : " + lstUser.size());
	    }
	    catch (Exception ex) {
	      
	    }
	    return "Exitoso!!";
	  }
	

	/**
	 * Metodo para devolver el JSON de las Lineas del LAYOUT EJEMPLO
	 */
	@RequestMapping(value="/getFileJSON/{IDFileType}", method=RequestMethod.GET)
	public @ResponseBody String getFileJSON(@PathVariable("IDFileType") String IDFileType){
		
		System.out.println("Numeor de LAYOUT RECIBIDO por PARAMETRO: " + IDFileType);
			  
		// Recuperar las Lineas del LAYOUT CORRESPONDIENTE
		List<ADB_FileLine> lstFileLines = new ArrayList<ADB_FileLine>();
		String JSON = "[";
		
			try{
				lstFileLines = userDao.getAllLinesByFile(IDFileType);
	
				
				
				for(int i = 0; i < lstFileLines.size(); i++){
/*
					JSON = JSON + "{\"IDFileType\": " + lstFileLines.get(i).getAdbFileDefinition().getIDFileType() + "," + 
		                                       "\"IDLineType\": " + "\""+lstFileLines.get(i).getIDLineType() + "\"," + 
		                                       "\"LineType\": " + "\""+lstFileLines.get(i).getLineType() + "\"," +
		                                       "\"StartLine\": " + "\""+lstFileLines.get(i).getStartLine() + "\"," +
		                                       "\"EndLine\": " + "\""+lstFileLines.get(i).getEndLine() + "\"," +
		                                       "\"StartIdentification\": " + "\""+lstFileLines.get(i).getStartIdentification() + "\"," +
		                                       "\"EndIdentification\": " + "\""+lstFileLines.get(i).getEndIdentification() + "\"," +
		                                       "\"IdentificationValue\": " + "\""+lstFileLines.get(i).getIdentificationValue()+"\"}";
 */

					JSON = JSON + "{\"LineType\": " + "\""+lstFileLines.get(i).getLineType() + "\"," +
                            "\"StartLine\": " + "\""+lstFileLines.get(i).getStartLine() + "\"," +
                            "\"EndLine\": " + "\""+lstFileLines.get(i).getEndLine() + "\"," +
                            "\"StartIdentification\": " + "\""+lstFileLines.get(i).getStartIdentification() + "\"," +
                            "\"EndIdentification\": " + "\""+lstFileLines.get(i).getEndIdentification() + "\"," +
                            "\"IdentificationValue\": " + "\""+lstFileLines.get(i).getIdentificationValue()+"\"}";
					
					if((i+1) != lstFileLines.size()){
						JSON = JSON + ",";
					}
				}
				JSON = JSON + "]";
				
			} catch(Exception ex){
				
			}
		
		return JSON;
	}
	
	
	
	
	/**
	 * Metodo para devolver el JSON de los campos del LAYOUT EJEMPLO
	 */
	@RequestMapping(value="/getFileFieldsJSON/{IDFileType}", method=RequestMethod.GET)
	public @ResponseBody String getFileFieldsJSON(@PathVariable("IDFileType") String IDFileType){
		
		// Recuperar las Lineas del LAYOUT CORRESPONDIENTE
		List<ADB_FileLine> lstFileLines = new ArrayList<ADB_FileLine>();
		String JSON = "[";
/*		
			try{
				// Obtener la lineas del layout
				lstFileLines = userDao.getAllLinesByFile(IDFileType);
				
				// Recuperar los campos por liena del Layout
				List<ADB_FileField> lstFileField = null;

				
				// Recuperar campos por Linea del LAYOUT
				lstFileField = new ArrayList<ADB_FileField>();
				lstFileField = userDao.getAllFieldsByFile(lstFileLines.get(0).getAdbFileDefinition().getIDFileType().toString(), "");
				
				for(int i = 0; i < lstFileField.size(); i++){
					JSON = JSON + "{\"IDLineType\": " + lstFileField.get(i).getAdbFileLine().getIDLineType() + "," + 
		                                       "\"IDField\": " + "\""+lstFileField.get(i).getIDField() + "\"," + 
		                                       "\"Field\": " + "\""+lstFileField.get(i).getField() + "\"," +
		                                       "\"LongField\": " + "\""+lstFileField.get(i).getLongField() + "\"," +
		                                       "\"StartField\": " + "\""+lstFileField.get(i).getStartField() + "\"," +
		                                       "\"EndField\": " + "\""+lstFileField.get(i).getEndField() + "\"," +
		                                       "\"IDFormat\": " + "\""+lstFileField.get(i).getAdbCtFieldFormat().getIDFormat() + "\"," +
		                                       "\"IDValidation\": " + "\""+lstFileField.get(i).getIDValidation() + "\"," +
		                                       "\"Recovered\": " + "\""+lstFileField.get(i).getRecovered() + "\"," +
		                                       "\"Save\": " + "\""+lstFileField.get(i).getSave()+"\"}";
					if((i+1) != lstFileField.size()){
						JSON = JSON + ",";
					}
				}
				JSON = JSON + "]";
				
				
			} catch(Exception ex){
				
			}
*/		
		return JSON;
	}
	
	
	
	
	
	/**
	 * Metodo para devolver el JSON de las Lineas del LAYOUT PROPUESTA
	 */
	@RequestMapping(value="/getFileWFieldsJSON/{IDFileType}", method=RequestMethod.GET)
	public @ResponseBody String getFileWFieldsJSON(@PathVariable("IDFileType") String IDFileType){
		
		System.out.println("Numeor de LAYOUT RECIBIDO por PARAMETRO: " + IDFileType);
			  
		// Recuperar las Lineas del LAYOUT CORRESPONDIENTE
		List<ADB_FileLine> lstFileLines = new ArrayList<ADB_FileLine>();
		String JSON = "{\"lineas\": [";
/*		
			try{
				lstFileLines = userDao.getAllLinesByFile(IDFileType);
				
				int countLines = 0;
				
				for(int i = 0; i < lstFileLines.size(); i++){
					JSON = JSON + "{\"IDFileType\": " + lstFileLines.get(i).getAdbFileDefinition().getIDFileType() + "," + 
		                                       "\"IDLineType\": " + "\""+lstFileLines.get(i).getIDLineType() + "\"," + 
		                                       "\"LineType\": " + "\""+lstFileLines.get(i).getLineType() + "\"," +
		                                       "\"StartLine\": " + "\""+lstFileLines.get(i).getStartLine() + "\"," +
		                                       "\"EndLine\": " + "\""+lstFileLines.get(i).getEndLine() + "\"," +
		                                       "\"StartIdentification\": " + "\""+lstFileLines.get(i).getStartIdentification() + "\"," +
		                                       "\"EndIdentification\": " + "\""+lstFileLines.get(i).getEndIdentification() + "\"," +
		                                       "\"IdentificationValue\": " + "\""+lstFileLines.get(i).getIdentificationValue()+"\"}";
					if((i+1) != lstFileLines.size()){
						JSON = JSON + ",";
					}
					countLines++;
				}
				JSON = JSON + "],\"campos\": [";
				
				
				
				// Recuperar los campos por liena del Layout
				List<ADB_FileField> lstFileField = null;
				lstFileField = new ArrayList<ADB_FileField>();
				

				for(int x=1; x<= countLines; x++){
					
					// Recuperar campos por Linea del LAYOUT
					lstFileField = userDao.getAllFieldsByFile(lstFileLines.get(0).getAdbFileDefinition().getIDFileType().toString(), String.valueOf(x));
					
					JSON = JSON + "{\"linea"+String.valueOf(x)+"\": [";
					
					for(int i = 0; i < lstFileField.size(); i++){
						JSON = JSON + "{\"IDLineType\": " + lstFileField.get(i).getAdbFileLine().getIDLineType() + "," + 
			                                       "\"IDField\": " + "\""+lstFileField.get(i).getIDField() + "\"," + 
			                                       "\"Field\": " + "\""+lstFileField.get(i).getField() + "\"," +
			                                       "\"LongField\": " + "\""+lstFileField.get(i).getLongField() + "\"," +
			                                       "\"StartField\": " + "\""+lstFileField.get(i).getStartField() + "\"," +
			                                       "\"EndField\": " + "\""+lstFileField.get(i).getEndField() + "\"," +
			                                       "\"IDFormat\": " + "\""+lstFileField.get(i).getAdbCtFieldFormat().getIDFormat() + "\"," +
			                                       "\"IDValidation\": " + "\""+lstFileField.get(i).getIDValidation() + "\"," +
			                                       "\"Recovered\": " + "\""+lstFileField.get(i).getRecovered() + "\"," +
			                                       "\"Save\": " + "\""+lstFileField.get(i).getSave()+"\"}";
						if((i+1) != lstFileField.size()){
							JSON = JSON + ",";
						}
					}
					
					if((x) != countLines){
						JSON = JSON + "]},";
					} else {
						JSON = JSON + "]}";
					}
				}
				
				
				JSON = JSON + "]}";
				
				
				
			} catch(Exception ex){
				
			}
		*/
		return JSON;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Metodo para la lectura del Layout y su validacion
	 */
	@RequestMapping(value="/getFile/{IDFileType}")
	@ResponseBody
	public String getFile(@PathVariable("IDFileType") String IDFileType){
		
		System.out.println("Numero de LAYOUT RECIBIDO por PARAMETRO: " + IDFileType);
		
		cargaArchivo(IDFileType);
		
		return "Carga del archivo Exitosa!!";
	}
	
	
	/**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	   */
	@RequestMapping(value="/getPrueba")
	  @ResponseBody
	  public String getPrueba() {
		  
		  List<PruebaPs> lstPersistence = new ArrayList<PruebaPs>();
		  
	    try {
	    	lstPersistence = userDao.getPrueba();
	    	System.out.println("HOLA!! : " + lstPersistence.size());
	    }
	    catch (Exception ex) {
	      
	    }
	    return "Exitoso!!";
	  }
	
	/**
	 * Invoke a Stored Procedure
	 */
	@RequestMapping(value="/getCallSP")
	@ResponseBody	
	public String getCallSP() {
		
		String result = "";
		
		try{
			result = userDao.getCallSP();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
  /**
   * Create a new user with an auto-generated id and email and name as passed 
   * values.
   
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User(email, name);
      userDao.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created!";
  }*/
  
  /**
   * Delete the user with the passed id.
   
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }*/
  
  /**
   * Retrieve the id for the user with the passed email address.
   
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "The user id is: " + userId;
  }*/
  
  
  /**
   * Retrieve the id for the user with the passed email address.
   
  @RequestMapping(value="/getAll")
  @ResponseBody
  public String getAll() {
    try {
      List<User> user = userDao.getAll();
      System.out.println("Exitoso: " + user.size());
    }
    catch (Exception ex) {
      return "User not found: " + ex.toString();
    }
    return "Exitoso!";
  }*/
  
  
  /**
   * Update the email and the name for the user indentified by the passed id.
   
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      User user = userDao.getById(id);
      user.setEmail(email);
      user.setName(name);
      userDao.update(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }*/ 
  
  
  
  
  public void cargaArchivo(String IDFileType){
	  
		try{
			// Recuperar las Lineas del LAYOUT CORRESPONDIENTE
			List<ADB_FileLine> lstFileLines = new ArrayList<ADB_FileLine>();
			lstFileLines = userDao.getAllLinesByFile(IDFileType);
			
			System.out.println("Total de Lineas por LAYOUT:" + lstFileLines.size());
			
			// Recuperar los campos por liena del Layout
			List<ADB_FileField> lstFileField = null;


			// Contabilizar dinamicamente el numero de lineas por ESTRUCTURA de LAYOUT
			FileCountFields countFile = null;
			List<FileCountFields> lstFileCountFields = new ArrayList<FileCountFields>();
			
			
			for(ADB_FileLine adb_FileLine : lstFileLines){
				
				// Recuperar campos por Linea del LAYOUT
				lstFileField = new ArrayList<ADB_FileField>();
				lstFileField = userDao.getAllFieldsByFile(String.valueOf(adb_FileLine.getAdbFileDefinition().getIDFileType()), String.valueOf(adb_FileLine.getIDLineType()));
				
				// Guardar campos por Linea de LAYOUT				
				countFile = new FileCountFields();
				countFile.setAdb_FileField(lstFileField);
				lstFileCountFields.add(countFile);

			}
			
			System.out.println("Total de Listas de Listas:" + lstFileCountFields.size());
			
			
			// Instanciar el lector de texto plano
			TextReader tr = new TextReader();
			
			// Lectura del archivo texto plano
			String ruta = "/home/angel/CargaArchivo/Testing/TEF_NAT_5_LayoutC";
			String banco = "Multiva";
			String fileName = "Nomina";
			
			tr.readText(ruta, lstFileLines, lstFileCountFields, IDFileType, banco, fileName);
			
//			userDao.createTxFile(adbCustomerFile);
			
		} catch(Exception ex){
			
		}
	  
  }
  
  
  
  
} // class UserController