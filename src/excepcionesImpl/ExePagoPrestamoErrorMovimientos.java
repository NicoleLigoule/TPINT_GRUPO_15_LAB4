package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;

public class ExePagoPrestamoErrorMovimientos extends ExcepcionesPagoPrestamo{

	private String mensajeError = "No se pudo cargar la operacion en el historial de movimientos.";
	
	@Override
	public void printErrorDetails() {
		System.out.print(mensajeError);
	}
	
	@Override
    public String toString() {
        return mensajeError;
    }

}
