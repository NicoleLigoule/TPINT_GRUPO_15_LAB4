package dao;

import entidades.CuotasXPrestamo;

import java.time.LocalDate;
import java.util.List;

public interface CuotasXPrestamoDao {
    void agregarCuota(CuotasXPrestamo cuota);
    void actualizarCuota(CuotasXPrestamo cuota);
    void eliminarCuota(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota);
    CuotasXPrestamo obtenerCuota(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota);
    List<CuotasXPrestamo> obtenerTodasLasCuotas();
    
}
