package netgloo.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import netgloo.beans.FileCountFields;
import netgloo.models.ADB_FileLine;
import netgloo.utils.Datatype;


public class TextReader extends Datatype{
	
	// Constantes para tipos de datos
	private static final char DATATYPE_NUMERIC = 'N';
	private static final char DATATYPE_ALPHANUMERIC = 'A';
	
	// Constantes para indicar si el tipo de dato es 'recuperable'
	private static final char RECOVERABLE_DATA = 'Y';
	private static final char NON_RECOVERABLE_DATA = 'N';
	
	// Constantes para indicar si el tipo de dato es 'recuperable'
	private static final char TRANSACTION_GLOBAL = '1';
	private static final char TRANSACTION_DETALLE = '2';
	
	
	
	public void readText(String ruta, List<ADB_FileLine> lstFileLines, List<FileCountFields> lstFileCountFields, String IDFileType, String banco, String fileName){
		
		// ER
		ExpresionesRegulares validar = new ExpresionesRegulares();
		
	    FileReader fr = null;
	    BufferedReader br = null;
	    //Cadena de texto donde se guardara el contenido del archivo
	    String contenido = "";
	    
	    // Datos para persistir informacion del contenido del archivo
	    
	   
	    
	    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    
	    // Retornar Map con detalle del archivo y valor Boolean para indicar el estatus de la lectura del archivo
	    Vector<Object> response = new Vector<Object>();
	    int countTx = 0;
	    int countTxRight = 0;
	    int countTxWrong = 0;
	    
	    try
	    {
	        //ruta puede ser de tipo String o tipo File
	        //si usamos un File debemos hacer un import de esta clase
	        //File ruta = new File( "/home/usuario/texto.txt" );
	        fr = new FileReader( ruta );
	        br = new BufferedReader( fr );
	  
	        String linea;
	        //Obtenemos el contenido del archivo linea por linea
	        while( ( linea = br.readLine() ) != null )
	        {
//	        	validar.validarCorreo(linea);
//	            contenido += linea + "\n";
	    		
	        	response = validarLinea(linea, lstFileLines, lstFileCountFields, IDFileType, banco, fileName, countTx);
	        	
	        	if(response.get(0) != null && (Boolean) response.get(0) == true){
	        		System.out.println("countTx++: " + ++countTx);
	        		++countTxRight;
	        	} else {
	        		++countTxWrong;
	        	}
	        	
	        	
	        	
//	        	if(registroCorrecto == false){
//	        		System.out.println("La estructura del archivo no es correcta");
//	        		break;
//	        	}
	        }
	        
	        
	        // Se llena la entidad para el control del archivo
	        //adbCustomerFile = new ADB_CustomerFile(banco, fileName, "1", 0, countTxRight, true, String.valueOf(date.format(new Date())), "Nomina", 0, countTxWrong, "P", 0, countTx);
//	        
	        
	    }catch( Exception e ){  }
	    //finally se utiliza para que si todo ocurre correctamente o si ocurre
	    //algun error se cierre el archivo que anteriormente abrimos
	    finally
	    {
	        try{
	            br.close();
	        }catch( Exception ex ){
	        	
	        	System.out.println( ex.getMessage() );
	        }
	    }
	    //Se imprime el contenido
	    System.out.println(contenido);
	    
	}
	
	
//	public static void main(String[] args){
//		
//		TextReader readerText = new TextReader();
//		String ruta = "/home/angel/CargaArchivo/Testing/TEF_NAT_5_LayoutC";
//		
//		readerText.readText(ruta);
//		
//	}
	
	
	public Vector<Object> validarLinea(String line, List<ADB_FileLine> lstFileLines, List<FileCountFields> lstFileCountFields, String IDFileType, String banco, String fileName, int countTx){
		
		// Retornar Map con detalle del archivo y valor Boolean para indicar el estatus de la lectura del archivo
		Vector<Object> response = new Vector<Object>();
		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		
		// Se obtiene el total de la linea
    	int longitudLinea = line.length();
    	int lineLengthSelected = 0;

    	// Bandera para identificar el tipo de linea contra la cual comparar
    	Integer lineSelected = null;
    	
    	// Bandera para identificar la naturaleza de la linea o transaccion referente a transacciones de tipo header(TRANSACTION_GLOBAL = 1), operation(TRANSACTION_DETALLE = 2) y footer (TRANSACTION_GLOBAL = 1)
    	String typeLine = null;
    	
    	// Bandera para identificar los campos del tipo de linea correpondiente
    	Integer lineCountSelected = null;
    	
    	// Bnadera para identificar si existe algun campo en error
    	boolean txCorrecta = true;
    	boolean txRecuperable = true;
    	
    	// Verificar el tipo de linea correpondiente
    	for(int i=0; i<lstFileLines.size(); i++){
    		
    		// Se obtiene el total de la linea y el tipo de registro
        	String tipoRegistro = line.substring(lstFileLines.get(i).getStartIdentification(), lstFileLines.get(i).getEndIdentification());
        	
    		if(lstFileLines.get(i).getIdentificationValue().trim().equals(tipoRegistro.trim())){
    			lineSelected = lstFileLines.get(i).getIDLineType();
    			typeLine = lstFileLines.get(i).getIdentificationLine();
    			
    			lineLengthSelected = lstFileLines.get(i).getEndLine();
    			
//    			if(lineLengthSelected != longitudLinea){
//    				System.out.println("La longitud de la linea no coincide con la definida en la configuración del Layout");
//
//    				if(response != null && response.size() == 0){
//    					response.addElement(false);
//    				}
////    				response.addElement(adbFileDetail);
//    		    	
//    				return response;
//    			}
    		}
    	}

///////////////////////////////////////////////////// VERIFICAR ///////////////////////////////////////////////
//    	if(lineSelected==null){
//    		System.out.println("El tipo de linea no esta declarada en la configuración del Layout");
//    		
//    		if(response != null && response.size() == 0){
//				response.addElement(false);
//			}
////			response.addElement(adbFileDetail);
//			
//    		return response;
//    	}
///////////////////////////////////////////////////// VERIFICAR ///////////////////////////////////////////////
    	
    	
    	// Bandera para identificar los campos correpondientes
    	for(int y=0; y<lstFileCountFields.size(); y++){
    		if(lstFileCountFields.get(y).getAdb_FileField().get(0).getAdbFileLine().getIDLineType()==lineSelected){
    			lineCountSelected = y;
    		}
    	}
    	
    	// Obtener el total de campos de la linea
    	Integer totalCampos = lstFileCountFields.get(lineCountSelected).getAdb_FileField().size();
    	
//    	private static final char RECOVERABLE_DATA = 'Y';
//    	private static final char NON_RECOVERABLE_DATA = 'N';
    	
    	for(int x=0; x<totalCampos; x++){
    		
    		String campo = line.substring(lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getStartField(), lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getEndField());
    		
    		// Se identifica el indice con el cual sera enviado al SP para su persistencia en BD
    		String saveIndSP = lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getSaved();
    		
    		if(lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getFormat().equals(String.valueOf(DATATYPE_NUMERIC))){
    			
    			if(!isNumeric(campo)){
    				System.out.println("isNumeric -" + campo);
    				System.out.println("isNumeric - El tipo de dato " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getField() + " no corresponde con lo especificado en el layout");
    	    		
//    				if(response != null && response.size() == 0){
//    					response.addElement(false);
//    				}
//    				response.addElement(adbFileDetail);
    				
    				txCorrecta = false;
//    				boolean txRecuperable = true;
    				
//    				return response;
    			}
    			
    		} else if (lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getFormat().equals(String.valueOf(DATATYPE_ALPHANUMERIC))){
    			
    			if(!esAlfaNumerica(campo)){
    				System.out.println("esAlfaNumerica -" + campo);
    				System.out.println("esAlfaNumerica - El tipo de dato " + lstFileCountFields.get(lineCountSelected).getAdb_FileField().get(x).getField() + " no corresponde con lo especificado en el layout");
    	    		
//    				if(response != null && response.size() == 0){
//    					response.addElement(false);
//    				}
//    				response.addElement(adbFileDetail);
    				
    				txCorrecta = false;
    				
//    	    		return response;
    			}
    			
    		}
    		
    		System.out.println(campo);
    		
    	}

//    	
//    	
    	
    	
    	
		
		
//
		
		

//-- ADB_FileDetails

//	IDFileType		// IDFileType
//Client			// banco
//FileName			// fileName
//NumberRegistry	// countTx
//Date				// date.format(new Date())
//Time				// time.format(new Date())
//Status			// YA
//Error				// YA
//	IDLineType		// tipoRegistro 

    	if(response != null && response.size() == 0){
			response.addElement(txCorrecta);
		}
    	
		response.addElement(null);
		
		return response;
	}
	
}
