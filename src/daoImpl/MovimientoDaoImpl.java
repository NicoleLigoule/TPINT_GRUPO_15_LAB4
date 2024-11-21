package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidades.Movimiento;

public class MovimientoDaoImpl implements MovimientoDao {
    private Conexion cn;

    @Override
    public List<Movimiento> obtenerMovimientos() {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            ResultSet rs = cn.query("SELECT * FROM Movimiento");
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();

                // Asignación de valores según la estructura de la base de datos
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                movimiento.setFechaMovimientoMov(rs.getTimestamp("FechaMovimiento_Mov").toLocalDateTime());
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));

                lista.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }
}
