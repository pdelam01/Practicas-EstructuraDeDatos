package ule.edi.exceptions;

public class TypeIsNotComparableExeception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TypeIsNotComparableExeception(String nameCollection) {
		super("El tipo de los elementos de la coleccion "+nameCollection+" no es comparable.");
	}

}
