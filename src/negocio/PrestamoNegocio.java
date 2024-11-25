package negocio;

import dao.PrestamoDao;
import daoImpl.PrestamoDaoImpl;
import entidades.Prestamo;

public class PrestamoNegocio {
    private PrestamoDao prestamoDao;

    public PrestamoNegocio() {
        this.prestamoDao = new PrestamoDaoImpl();
    }

    public boolean realizarPagoPrestamo(int idPrestamo, double monto) {
        // Validar negocio: no se permite pagar montos negativos o cero
        if (monto <= 0) {
            return false;
        }

        Prestamo prestamo = prestamoDao.obtenerPrestamoPorId(idPrestamo);
        if (prestamo == null) {
            return false; // El préstamo no existe
        }

        double saldoRestante = prestamo.getImporteSolicitadoPt().doubleValue() - monto;
        System.out.printf("Saldo Restante: ", saldoRestante);
     
        
        if (saldoRestante < 0) {
            return false;
        }

        if (saldoRestante == 0) {
            prestamo.setEstadoPt(true); // Pagado
        }

        return prestamoDao.actualizarPrestamo(prestamo);
    }
}
