package negocio;


import java.time.LocalDate;
import java.util.List;

import daoImpl.ClienteDaoImpl;
import daoImpl.CuentaDaoImpl;
import daoImpl.ProvinciaDaoImpl;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.TipoDeCuenta;;

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
	 
	 public boolean agregarCuenta(Cuenta cuenta) {
			if(cuenta.getCuilCliCu() != null) {
				return cuentaDao.insertar(cuenta);
			}else {
				return cuentaDao.insertarSinCliente(cuenta);
			}

		}
		
		public boolean editarCuenta(Cuenta cuenta) {
			return cuentaDao.editar(cuenta);
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
		
		public List<Cuenta> obtenerCuentasReporte(int tipo,LocalDate fechaDesde, LocalDate fechaHasta) {
		    return cuentaDao.obtenerCuentasReporte(tipo, fechaDesde, fechaHasta);
		}

		public Cliente obtenerClienteDeLACuenta(String numeroCuenta) {
			String cuil= cuentaDao.obtenerCuilCuentaPorNumero(numeroCuenta);
			ClienteDaoImpl clienteDao =new 	ClienteDaoImpl();
			Cliente cli=clienteDao.obtenerUnoPorCuil(cuil);
			return cli;
		}
		
	public double PorcentajeCreditosAprobados() {
		ProvinciaDaoImpl dao=new ProvinciaDaoImpl();
	double porcentaje = dao.Porcentajes_p_aprobados();
	return porcentaje;
	}
	public double RendimientosCreditosAprobados() {
		ProvinciaDaoImpl dao=new ProvinciaDaoImpl();
	double porcentaje = dao.Rendimientos_p_aprobados();
	return porcentaje;
	}
	public double RendimientosMEnsualesCreditosAprobados() {
		ProvinciaDaoImpl dao=new ProvinciaDaoImpl();
	double porcentaje = dao.RendimientosMEnsuales_p_aprobados();
	return porcentaje;
	}
}
