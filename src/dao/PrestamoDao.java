package dao;

import java.util.List;
import entidades.Prestamo;

public interface PrestamoDao {
	
    boolean insertarPrestamo(Prestamo prestamo);
    boolean actualizarPrestamo(Prestamo prestamo);
    boolean eliminarPrestamo(int idPrestamo);
    List<Prestamo> obtenerPrestamos();
    Prestamo obtenerPrestamoPorId(int idPrestamo);
	boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, int plazoPago,
			double montoPorCuota);
	boolean comprobarPlazoExistente(int plazoPago);
}

