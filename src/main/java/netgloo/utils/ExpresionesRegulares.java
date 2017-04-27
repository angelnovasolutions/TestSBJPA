package netgloo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpresionesRegulares {

	public void validarCorreo(String email){
				
		        Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		        Matcher mat = pat.matcher(email);
		
		        if(mat.find()){
		           System.out.println("Correo Válido");
		        }else{
		           System.out.println("Correo No Válido");
		        }		
		
	}
	
}
