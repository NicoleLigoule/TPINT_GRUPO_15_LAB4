package negocio;

import java.util.List;
import daoImpl.MovimientoDaoImpl;
import entidades.Movimiento;

public class NegocioMovimiento {
    private MovimientoDaoImpl movimientoDao;

    public NegocioMovimiento() {
        this.movimientoDao = new MovimientoDaoImpl();
    }

    public List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta) {
        return movimientoDao.obtenerMovimientosPorCuenta(numeroCuenta);
    }

    public List<Movimiento> obtenerMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento) {
        return movimientoDao.obtenerMovimientosPorCuentaYTipo(numeroCuenta, tipoMovimiento);
    }
}
