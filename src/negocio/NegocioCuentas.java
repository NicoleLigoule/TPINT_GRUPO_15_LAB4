package negocio;


import daoImpl.CuentaDaoImpl;;

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
}
