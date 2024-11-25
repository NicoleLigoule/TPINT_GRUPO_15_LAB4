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

      
        // Método público para obtener los préstamos por cuenta
        //public List<Prestamo> obtenerPrestamoPorCuenta(int idCuenta) {
      //      return prestamoDao.obtenerPrestamosPorCuenta(idCuenta); // Ajusta la lógica según tu DAO
       // }
        
        /*public List<Prestamo> obtenerPrestamoPorCuenta(int numeroCuenta) {
            // Asegúrate de usar la instancia de PrestamoDao correctamente
            List<Prestamo> prestamos = prestamoDao.obtenerPrestamoPorCuenta(numeroCuenta);
            // Aquí puedes trabajar con la lista de préstamos
            return prestamos;
        }*/

        
        // Obtener el préstamo desde la base de datos
        Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
        if (prestamo == null) {
            return false; // El préstamo no existe
        }

        // Verificar si el préstamo ya está pagado
        if (prestamo.isEstadoPt()) {
            return false; // El préstamo ya está pagado
        }

        // Calcular el saldo restante
        double saldoRestante = prestamo.getImporteSolicitadoPt().doubleValue() - monto;

        // Si el saldo restante es menor a 0, no se puede pagar más del saldo pendiente
        if (saldoRestante < 0) {
            return false; // El monto excede el saldo pendiente
        }

        // Si el saldo es 0, marcar el préstamo como pagado
        if (saldoRestante == 0) {
            prestamo.setEstadoPt(true); // Marcar como pagado
        }

        // Actualizar el préstamo con el nuevo saldo o estado
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
