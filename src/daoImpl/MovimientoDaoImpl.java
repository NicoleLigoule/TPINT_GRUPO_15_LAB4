package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import dao.MovimientoDao;
import entidades.Movimiento;
import entidades.TipoMovimiento;

public class MovimientoDaoImpl implements MovimientoDao {
    private Conexion cn;

    @Override
    public List<Movimiento> obtenerMovimientosPorCuenta(int numeroCuenta) {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT m.*, tm.Descripcion_TM " +
                    "FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = " + numeroCuenta;

            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                String fechaMovimiento = rs.getString("FechaMovimiento_Mov");
                movimiento.setFechaMovimientoMov(fechaMovimiento);
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));
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

    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento) {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT m.*, tm.Descripcion_TM " +
                    "FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = " + numeroCuenta + " AND tm.Descripcion_TM = '" + tipoMovimiento + "'";

            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                String fechaMovimiento = rs.getString("FechaMovimiento_Mov");
                movimiento.setFechaMovimientoMov(fechaMovimiento);
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));
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
    
    
    @Override
    public List<TipoMovimiento> obtenerTipoMovimientos(){
		
    	cn = new Conexion();
        cn.Open();
        List<TipoMovimiento> lista = new ArrayList<>();
        try {
            String query = "SELECT * FROM TipoMovimiento";

            ResultSet rs = cn.query(query);
            while (rs.next()) {
            	TipoMovimiento tipo = new TipoMovimiento();
                
            	tipo.setIdTipoMovTM(rs.getInt("Id_TipoMov_TM"));
            	tipo.setDescripcionTM(rs.getString("Descripcion_TM"));            	
                lista.add(tipo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
    	
    	
    	return lista;
    	
    }
    
    @Override
    public boolean agregarMovimiento(int numCuenta, String detalle, int tipoMv, BigDecimal monto) {
    	cn = new Conexion();
        cn.Open();
        boolean exito = false;
        try {
        	 String insertMovimiento = "INSERT INTO Movimiento (Detalle_Mov, Importe_Mov, Id_TipoMov_TM_Mov) VALUES (?, ?, ?)";
             String insertMovimientoXCuenta = "INSERT INTO MovimientoXCuenta (Id_Movimiento__Mov_MXC, Num_Cuenta_Cu_MXC) VALUES (?, ?)";
             
             PreparedStatement movimientoStmt = null;
             PreparedStatement movimientoXCuentaStmt = null;
             ResultSet generatedKeys = null;
             
             
             movimientoStmt = (PreparedStatement)cn.getSQLConexion().prepareStatement(insertMovimiento,Statement.RETURN_GENERATED_KEYS);
             
             movimientoStmt.setString(1, detalle);
             movimientoStmt.setBigDecimal(2, monto);
             movimientoStmt.setInt(3, tipoMv);
             exito = movimientoStmt.executeUpdate()> 0;
             
             generatedKeys = movimientoStmt.getGeneratedKeys();
             
             if (generatedKeys.next()) {
                 int idMovimiento = generatedKeys.getInt(1);

                 // Insertar en la tabla MovimientoXCuenta
                 movimientoXCuentaStmt = (PreparedStatement)cn.getSQLConexion().prepareStatement(insertMovimientoXCuenta);
                 movimientoXCuentaStmt.setInt(1, idMovimiento);
                 movimientoXCuentaStmt.setInt(2, numCuenta);
                 exito = exito && (movimientoXCuentaStmt.executeUpdate() > 0);
             }
    	
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
    	
		return exito;
    	
    }
    
    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaPaginado(int numeroCuenta, int pagina, int limite) {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT m.*, tm.Descripcion_TM " +
                    "FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = ? " +
                    "LIMIT ?, ?";
            PreparedStatement stmt = (PreparedStatement) cn.getSQLConexion().prepareStatement(query);
            stmt.setInt(1, numeroCuenta);
            stmt.setInt(2, (pagina - 1) * limite); // Desplazamiento para la página
            stmt.setInt(3, limite);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                String fechaMovimiento = rs.getString("FechaMovimiento_Mov");
                movimiento.setFechaMovimientoMov(fechaMovimiento);
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));
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

    @Override
    public List<Movimiento> obtenerMovimientosPorCuentaYTipoPaginado(int numeroCuenta, String tipoMovimiento, int pagina, int limite) {
        cn = new Conexion();
        cn.Open();
        List<Movimiento> lista = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT m.*, tm.Descripcion_TM " +
                    "FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = ? AND tm.Descripcion_TM = ? " +
                    "LIMIT ?, ?";
            PreparedStatement stmt = (PreparedStatement) cn.getSQLConexion().prepareStatement(query);
            stmt.setInt(1, numeroCuenta);
            stmt.setString(2, tipoMovimiento);
            stmt.setInt(3, (pagina - 1) * limite); // Desplazamiento para la página
            stmt.setInt(4, limite);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movimiento movimiento = new Movimiento();
                movimiento.setIdMovimientoMov(rs.getInt("Id_Movimiento_Mov"));
                String fechaMovimiento = rs.getString("FechaMovimiento_Mov");
                movimiento.setFechaMovimientoMov(fechaMovimiento);
                movimiento.setDetalleMov(rs.getString("Detalle_Mov"));
                movimiento.setImporteMov(rs.getBigDecimal("Importe_Mov"));
                movimiento.setIdTipoMovTMMov(rs.getInt("Id_TipoMov_TM_Mov"));
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

    @Override
    public int contarMovimientosPorCuenta(int numeroCuenta) {
        cn = new Conexion();
        cn.Open();
        int total = 0;
        try {
            String query = "SELECT COUNT(*) FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = ?";
            PreparedStatement stmt = (PreparedStatement) cn.getSQLConexion().prepareStatement(query);
            stmt.setInt(1, numeroCuenta);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return total;
    }
    
    @Override
    public int contarMovimientosPorCuentaYTipo(int numeroCuenta, String tipoMovimiento) {
        cn = new Conexion();
        cn.Open();
        int total = 0;
        try {
            String query = "SELECT COUNT(*) FROM MovimientoXCuenta mx " +
                    "JOIN Movimiento m ON mx.Id_Movimiento__Mov_MXC = m.Id_Movimiento_Mov " +
                    "JOIN TipoMovimiento tm ON m.Id_TipoMov_TM_Mov = tm.Id_TipoMov_TM " +
                    "WHERE mx.Num_Cuenta_Cu_MXC = ? AND tm.Descripcion_TM = ?";
            PreparedStatement stmt = (PreparedStatement) cn.getSQLConexion().prepareStatement(query);
            stmt.setInt(1, numeroCuenta);
            stmt.setString(2, tipoMovimiento);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return total;
    }

    
    
}
