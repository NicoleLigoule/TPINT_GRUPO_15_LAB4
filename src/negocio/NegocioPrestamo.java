package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.CuotasXPrestamoDao;
import dao.PrestamoDao;
import daoImpl.CuotasXPrestamoDaoImpl;
import daoImpl.PrestamoDaoImpl;
import entidades.CuotasXPrestamo;
import entidades.DetalleXPrestamo;
import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.InteresesXCantidadDeMeses;
import entidades.Prestamo;


public class NegocioPrestamo {
    private PrestamoDao prestamoDao;
    
    public NegocioPrestamo() {
        this.prestamoDao = new PrestamoDaoImpl();
        
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

    
    public boolean realizarPagoPrestamo(int idPrestamo, double monto) {
        if (monto <= 0) {
            return false;
        }

      
        // M�todo p�blico para obtener los pr�stamos por cuenta
        //public List<Prestamo> obtenerPrestamoPorCuenta(int idCuenta) {
      //      return prestamoDao.obtenerPrestamosPorCuenta(idCuenta); // Ajusta la l�gica seg�n tu DAO
       // }
        
        /*public List<Prestamo> obtenerPrestamoPorCuenta(int numeroCuenta) {
            // Aseg�rate de usar la instancia de PrestamoDao correctamente
            List<Prestamo> prestamos = prestamoDao.obtenerPrestamoPorCuenta(numeroCuenta);
            // Aqu� puedes trabajar con la lista de pr�stamos
            return prestamos;
        }*/

        
        // Obtener el pr�stamo desde la base de datos
        Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
        if (prestamo == null) {
            return false; // El pr�stamo no existe
        }

        // Verificar si el pr�stamo ya est� pagado
        if (prestamo.isEstadoPt()) {
            return false; // El pr�stamo ya est� pagado
        }

        // Calcular el saldo restante
        double saldoRestante = prestamo.getImporteSolicitadoPt().doubleValue() - monto;

        // Si el saldo restante es menor a 0, no se puede pagar m�s del saldo pendiente
        if (saldoRestante < 0) {
            return false; // El monto excede el saldo pendiente
        }

        // Si el saldo es 0, marcar el pr�stamo como pagado
        if (saldoRestante == 0) {
            prestamo.setEstadoPt(true); // Marcar como pagado
        }

        // Actualizar el pr�stamo con el nuevo saldo o estado
        boolean exito = prestamoDao.actualizarPrestamo(prestamo);
        if (!exito) {
            return false; // Si hubo un error al actualizar, retornar false
        }

        // Si todo fue exitoso, retornar true
        return true;
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
	     // Si el plazo es v�lido, guardar el pr�stamo
        PrestamoDaoImpl prestamoDao = new PrestamoDaoImpl();

	    boolean guardado = prestamoDao.guardarPrestamo(cuentaDestino, importeSolicitado, montoConInteres, plazoPago, Detalle );
    	System.out.print("procesarPrestamo::SE PROCESA EL PRESTAMO");
        return guardado; 
    }

}
