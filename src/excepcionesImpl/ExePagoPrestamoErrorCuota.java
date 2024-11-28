package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;

public class ExePagoPrestamoErrorCuota extends ExcepcionesPagoPrestamo {

	private String mensajeError = "Fallo del sistema al abonar la cuota.";
	
	
	@Override
	public void printErrorDetails() {
		System.out.print(mensajeError);
		
	}

	@Override
    public String toString() {
        return mensajeError;
    }
	
}
