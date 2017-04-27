package netgloo.utils;

public class Datatype {

	public static boolean isNumeric(String cadena){
	    for(int i = 0; i < cadena.length(); ++i) {
	        char caracter = cadena.charAt(i);
	 
	        if(!Character.isDigit(caracter)) {
	            return false;
	        }

	    }
	    return true;
	}
	
	public static boolean esAlfaNumerica(final String cadena) {
	    for(int i = 0; i < cadena.length(); ++i) {
	        char caracter = cadena.charAt(i);
	 
	        if(!Character.isLetterOrDigit(caracter)) {
	        	if(!Character.isWhitespace(caracter)){
	        		return false;
	        	}
	        }

	    }
	    return true;
	}
	
}
