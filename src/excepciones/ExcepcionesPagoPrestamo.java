package excepciones;

public abstract class ExcepcionesPagoPrestamo extends Exception {
	
	public ExcepcionesPagoPrestamo() {
		
	}
	
	public abstract void printErrorDetails();
	
	
}
