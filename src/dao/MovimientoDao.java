package dao;

import java.util.List;
import entidades.Movimiento;

public interface MovimientoDao {
    List<Movimiento> obtenerMovimientos(); // Método para listar movimientos
}
