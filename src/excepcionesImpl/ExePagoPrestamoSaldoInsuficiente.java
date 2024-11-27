package excepcionesImpl;

import excepciones.ExcepcionesPagoPrestamo;


public class ExePagoPrestamoSaldoInsuficiente extends ExcepcionesPagoPrestamo {


    public ExePagoPrestamoSaldoInsuficiente() {

    }

	@Override
	public void printErrorDetails() {
		System.out.print("======EXEPCION SALDO INSUFICIENTE");
		
	}

}
