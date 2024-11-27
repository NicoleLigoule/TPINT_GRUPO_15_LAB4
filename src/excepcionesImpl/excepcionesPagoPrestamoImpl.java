package excepcionesImpl;

import excepciones.excepcionesPagoPrestamo;
import negocio.NegocioCuentas;
import negocio.NegocioPrestamo;

public class excepcionesPagoPrestamoImpl implements excepcionesPagoPrestamo {

    private final NegocioPrestamo prestamoNegocio;
    private final NegocioCuentas cuentasNegocio;

    public excepcionesPagoPrestamoImpl() {
        this.prestamoNegocio = new NegocioPrestamo();
        this.cuentasNegocio = new NegocioCuentas();
    }

    @Override
    public void validarPrestamoExistente(int idPrestamo) throws Exception {
        if (prestamoNegocio.PrestamoCargado(idPrestamo) == null) {
            throw new Exception("El pr�stamo con ID " + idPrestamo + " no existe.");
        }
    }

    @Override
    public void validarCuentaExistente(int numeroCuenta) throws Exception {
        if (cuentasNegocio.obtenerCuenta(numeroCuenta) == null) {
            throw new Exception("La cuenta con n�mero " + numeroCuenta + " no existe.");
        }
    }

    @Override
    public void validarUsuarioAutenticado(Object usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("El usuario no est� autenticado.");
        }
    }
}
