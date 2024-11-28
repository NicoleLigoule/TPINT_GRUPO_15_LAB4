package dao;

import java.util.ArrayList;
import java.util.List;

import entidades.CuotasXPrestamo;
import entidades.DetalleXPrestamo;
import entidades.InteresesXCantidadDeMeses;
import entidades.Prestamo;

public interface PrestamoDao {
	
	int insertarPrestamo(Prestamo prestamo);
    boolean actualizarPrestamo(Prestamo prestamo);
 //   boolean eliminarPrestamo(int idPrestamo);
    List<Prestamo> obtenerPrestamos();
    List<Prestamo> ListarPrestamosAprobar();
    Prestamo obtenerPrestamoPorId(int idPrestamo);
//	boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago, double montoPorCuota);
	boolean comprobarPlazoExistente(String plazoPago);
	public List<Prestamo> obtenerPrestamoPorCuenta(int numeroCuenta);
	public DetalleXPrestamo TraerDetalles(int idPrestamoPt);
	public ArrayList<CuotasXPrestamo> TraerCuotas(int idPrestamoPt);
	boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago,
			String motivo);

	
	/*static int obtenerCuotasPagadas(int idPrestamoPt) {
		
		return 0;
	}*/
	public InteresesXCantidadDeMeses obtenerIntereses(String iDInteres);
	

}

