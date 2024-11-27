package dao;

import java.math.BigDecimal;
import java.util.List;
import entidades.Movimiento;
import entidades.TipoMovimiento;


public interface MovimientoDao {
    List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta);
    List<Movimiento> obtenerMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento);
	List<TipoMovimiento> obtenerTipoMovimientos();
	boolean agregarMovimiento(int numCuenta, String detalle, int tipoMv, BigDecimal monto);
}
