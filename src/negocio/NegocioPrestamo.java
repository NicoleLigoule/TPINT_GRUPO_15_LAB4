package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import dao.CuotasXPrestamoDao;
import dao.MovimientoDao;
import dao.PrestamoDao;
import daoImpl.CuentaDaoImpl;
import daoImpl.CuotasXPrestamoDaoImpl;
import daoImpl.MovimientoDaoImpl;
import daoImpl.PrestamoDaoImpl;
import entidades.Cuenta;
import entidades.CuotasXPrestamo;
import entidades.DetalleXPrestamo;
import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.InteresesXCantidadDeMeses;
import entidades.Prestamo;


public class NegocioPrestamo {
    private PrestamoDao prestamoDao;
    private CuentaDao cuentaDao;
    private MovimientoDao movDao;
    
    public NegocioPrestamo() {
        this.prestamoDao = new PrestamoDaoImpl();
        this.cuentaDao = new CuentaDaoImpl();
        this.movDao = new MovimientoDaoImpl();
    }
    
    // me devuelve un prestamo totalmente cargado (lista de cuotas, descripcion)
    public Prestamo PrestamoCargado(int id) {
    	Prestamo prestamo = null;
    	
    	prestamo = prestamoDao.obtenerPrestamoPorId(id);
    	ArrayList<CuotasXPrestamo> cuotas = prestamoDao.TraerCuotas(id);    	
    	DetalleXPrestamo detalle = prestamoDao.TraerDetalles(id);     
    	
        if (prestamo != null) {
            prestamo.setCuotas(cuotas);
            prestamo.setDetalle(detalle);
        }
            
    	return prestamo;
    }

    
    public boolean realizarPagoPrestamo(int idPrestamo, int idCuenta) {
    	boolean exito = false;
    	Cuenta cu =cuentaDao.obtenerUno(idCuenta);
    	Prestamo pr = PrestamoCargado(idPrestamo);
    	
    	BigDecimal saldo = cu.getSaldoCu();
    	BigDecimal montoxCuota = pr.getDetalle().getImporteXCuotasDt();
    	saldo = saldo.subtract(montoxCuota);
    	
    	if(saldo.compareTo(BigDecimal.ZERO) >= 0) {
    		pagarCuota(idPrestamo);
    		
//    		descuenta de cuenta
    		cu.setSaldoCu(saldo);
    		exito = cuentaDao.editar(cu);
    		
//    		añade registro de movimiento
    		String detalleMov = "se pago cuota del prestamo de la cuenta " + Integer.toString(idCuenta);
//    		(1, 'Alta de cuenta'),
//    		(2, 'Transferencia'),
//          (3, 'Alta de prestamo'),
//    		(4, 'Pago de prestamo');

    		exito = exito && movDao.agregarMovimiento(idCuenta, detalleMov, 4, montoxCuota); //sabemos que el 4 es el pago de un prestamo
    		
    	}
        return exito;
    }


	public List<Prestamo> obtenerPrestamoPorCuenta(int idCuenta) {
		return prestamoDao.obtenerPrestamoPorCuenta(idCuenta);
	}
	
	public List<Prestamo> obtenerPrestamoPorCuentaConDetalle(int idCuenta) {
		List<Prestamo> prestamos = prestamoDao.obtenerPrestamoPorCuenta(idCuenta);
		
		
		for(Prestamo pr : prestamos) {
			pr.setDetalle(prestamoDao.TraerDetalles(pr.getIdPrestamoPt()));
			pr.setCuotas(prestamoDao.TraerCuotas(pr.getIdPrestamoPt()));
		}
		
		
		
		
		return prestamos;
	}
	
	
	public Prestamo obtenerPrestamo(int IDprestamo) {
		Prestamo pres= prestamoDao.obtenerPrestamoPorId(IDprestamo);
		return pres;
		
	}
	public InteresesXCantidadDeMeses InteresesdelPrestamo(String IDInteres) {
		InteresesXCantidadDeMeses interes= prestamoDao.obtenerIntereses(IDInteres);
		
		return interes;	
	}
	
	public boolean DaraltaPrestamos(Prestamo Pres) {
		boolean alta=prestamoDao.actualizarPrestamo(Pres);

		return alta;
		
	}
    public boolean procesarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago,String Detalle) {
    	//aca se carga en la db
	     // Si el plazo es vï¿½lido, guardar el prï¿½stamo
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();

	    boolean guardado = prestamoDao.guardarPrestamo(cuentaDestino, importeSolicitado, montoConInteres, plazoPago, Detalle );
    	System.out.print("procesarPrestamo::SE PROCESA EL PRESTAMO");
        return guardado; 
    }
    
    public boolean pagarCuota(int idPrestamo) {
    	boolean pagada = false;
    	PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();
    	CuotasXPrestamoDaoImpl cuotaDao = new CuotasXPrestamoDaoImpl();
    	ArrayList<CuotasXPrestamo> cu = prestamoDao.TraerCuotas(idPrestamo);
    	for(CuotasXPrestamo cuota: cu) {
    		if(cuota.getPagada() == false) {
    			pagada = cuotaDao.pagarCuota(cuota);    
    			break;
    		}
    	}
    	
    	
    	return pagada;
    	
    }

}
