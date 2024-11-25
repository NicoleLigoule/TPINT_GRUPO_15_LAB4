package negocio;

import java.util.List;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.Prestamo;


public class NegocioPrestamo {
    private PrestamoDao prestamoDao;
    public NegocioPrestamo() {
        this.prestamoDao = new PrestamoDaoImpl();
    }
    

    public boolean realizarPagoPrestamo(int idPrestamo, double monto) {
        // Validar negocio: no se permite pagar montos negativos o cero
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
		// TODO Auto-generated method stub
		return null;
	}

}
