package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;

public class ExePagoPrestamoErrorCuota extends ExcepcionesPagoPrestamo {

	@Override
	public void printErrorDetails() {
		System.out.print("EXCEPCION CUOTA ERROR");
		
	}

}
