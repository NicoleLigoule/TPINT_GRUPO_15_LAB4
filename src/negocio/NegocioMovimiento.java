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

    public List<Movimiento> obtenerMovimientosPorCuentaPaginado(int numeroCuenta, int pagina, int limite) {
        return movimientoDao.obtenerMovimientosPorCuentaPaginado(numeroCuenta, pagina, limite);
    }

    public int contarMovimientosPorCuenta(int numeroCuenta) {
        return movimientoDao.contarMovimientosPorCuenta(numeroCuenta);
    }
    
    public List<Movimiento> obtenerMovimientosPorCuentaYTipoPaginado(int numeroCuenta, String tipoMovimiento, int pagina, int limite) {
        return movimientoDao.obtenerMovimientosPorCuentaYTipoPaginado(numeroCuenta, tipoMovimiento, pagina, limite);
    }

    
    public int contarMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento) {
        return movimientoDao.contarMovimientosPorCuentaYTipo(numeroCuenta, tipoMovimiento);
    }
}
