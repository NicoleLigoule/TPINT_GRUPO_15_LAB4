package abml;

import java.util.List;

import daoImpl.CuentaDaoImpl;
import entidades.Cuenta;
import entidades.TipoDeCuenta;

public class abmlCuenta {
	
	private CuentaDaoImpl cuentaDao; 
	
	
	public abmlCuenta() {
		cuentaDao = new CuentaDaoImpl();
	}
	
	public boolean agregarCuenta(Cuenta cuenta) {
		return cuentaDao.insertar(cuenta);
	}
	
	public boolean editarCuenta(Cuenta cuenta) {
		return cuentaDao.editar(cuenta);
	}
	
	public boolean eliminarCuenta(int id) {
		return cuentaDao.borrar(id);
	}
	
	public Cuenta obtenerCuenta(int id) {
		return cuentaDao.obtenerUno(id);
	}
	
	public List<Cuenta> obtenerCuentaTodos() {
		return cuentaDao.obtenerTodos();
	}

	public List<Cuenta> obtenerCuentasPorCuil(String cuil) {
	    return cuentaDao.obtenerCuentasPorCuil(cuil);
	}
	
	public List<TipoDeCuenta> obtenerTiposTodos() {
		return cuentaDao.readallTipoDeCuentas();
	}
}
