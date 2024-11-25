package dao;

import java.util.List;

import entidades.InteresesXCantidadDeMeses;
import entidades.Prestamo;

public interface PrestamoDao {
	
	int insertarPrestamo(Prestamo prestamo);
    boolean actualizarPrestamo(Prestamo prestamo);
    boolean eliminarPrestamo(int idPrestamo);
    List<Prestamo> obtenerPrestamos();
    Prestamo obtenerPrestamoPorId(int idPrestamo);
	boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago,
			double montoPorCuota);
	boolean comprobarPlazoExistente(String plazoPago);
	
	public List<Prestamo> obtenerPrestamoPorCuenta(int numeroCuenta);
	static int obtenerCuotasPagadas(int idPrestamoPt) {
		
		return 0;
	}
	

}

