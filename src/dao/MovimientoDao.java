package dao;

import java.math.BigDecimal;
import java.util.List;
import entidades.Movimiento;
import entidades.TipoMovimiento;

public interface MovimientoDao {
    List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta);
    List<Movimiento> obtenerMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento);
    List<Movimiento> obtenerMovimientosPorCuentaPaginado(int numeroCuenta, int pagina, int limite);
    List<Movimiento> obtenerMovimientosPorCuentaYTipoPaginado(int numeroCuenta, String tipoMovimiento, int pagina, int limite);
    int contarMovimientosPorCuenta(int numeroCuenta);  
    int contarMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento);  // Agregamos esta función
    List<TipoMovimiento> obtenerTipoMovimientos();
    boolean agregarMovimiento(int numCuenta, String detalle, int tipoMv, BigDecimal monto);
}
