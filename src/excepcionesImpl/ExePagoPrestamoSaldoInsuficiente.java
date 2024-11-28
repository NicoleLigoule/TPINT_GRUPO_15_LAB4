package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;


public class ExePagoPrestamoSaldoInsuficiente extends ExcepcionesPagoPrestamo {

	private String mensajeError = "No posee el SALDO suficiente para realizar esta operacion.";

	@Override
	public void printErrorDetails() {
		System.out.print(mensajeError);
	}
	
	@Override
    public String toString() {
        return mensajeError;
	}

}
