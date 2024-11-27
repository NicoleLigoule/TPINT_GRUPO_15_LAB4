package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;

public class ExePagoPrestamoErrorMovimientos extends ExcepcionesPagoPrestamo{

	@Override
	public void printErrorDetails() {
		System.out.print("EXCEPCION MOVIMIENTO ERROR");
		
	}

}
