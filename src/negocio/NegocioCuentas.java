package negocio;


import java.util.List;

import daoImpl.CuentaDaoImpl;
import entidades.Cuenta;;

public class NegocioCuentas {

	 private CuentaDaoImpl cuentaDao;
	 
	 public NegocioCuentas() {
	        this.cuentaDao = new CuentaDaoImpl();
	    }
	 
	 public boolean eliminarCuenta(int numeroCuenta) 
	 {
		 if (numeroCuenta == 0 ) {
	            System.out.println("Error: CUIL invalido.");
	            return false;
	        }
		 
		 return cuentaDao.borrar(numeroCuenta);
	 }

	 public List<Cuenta> obtenerCuentasPorCuil(String cuil) {
		    // Llamar al método de la capa Dao para obtener las cuentas filtradas por CUIL
		    return cuentaDao.obtenerCuentasPorCuil(cuil);
		}


}
