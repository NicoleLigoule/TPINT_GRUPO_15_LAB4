package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

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

                // Asignaci�n de valores seg�n la estructura de la base de datos
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
    
    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta) {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            // Construir la consulta con eliminaci�n de duplicados
            String query = "SELECT DISTINCT m.*, tm.Descripcion_TM " +
                    "FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = " + numeroCuenta;

            
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();

                // Mapear las columnas a las propiedades de la clase Movimiento
             // Mapear las columnas a las propiedades de la clase Movimiento
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                movimiento.setFechaMovimientoMov(rs.getTimestamp("FechaMovimiento_Mov").toLocalDateTime());
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));
                // Asignamos la descripci�n del tipo de movimiento
                movimiento.setDescripcionTipoMov(rs.getString("Descripcion_TM"));
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
