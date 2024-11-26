package dao;

import java.util.List;
import entidades.Movimiento;

public interface MovimientoDao {
    List<Movimiento> obtenerMovimientos();
    List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta); // Nuevo método
}
