package daoImpl;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entidades.Cuenta;
import entidades.InteresesXCantidadDeMeses;
import entidades.Prestamo;
import dao.PrestamoDao;
import dao.CuentaDao;

public class PrestamoDaoImpl implements PrestamoDao {
    private Conexion conexion;

    public PrestamoDaoImpl() {
        this.conexion = Conexion.getConexion();
    }

    @Override
    public boolean insertarPrestamo(Prestamo prestamo) {
        boolean resultado = false;
        String query = "INSERT INTO Prestamo (Numero_de_Cuenta_Cu_Pt, Importe_solicitado_Pt, Plazo_Pago_Pt, Detalle_solicitud_Pt, Estado_Pt) VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, prestamo.getNumeroDeCuentaCuPt());
            ps.setBigDecimal(2, prestamo.getImporteSolicitadoPt());
            ps.setString(3, prestamo.getPlazoPagoPt());
            ps.setString(4, prestamo.getDetalleSolicitudPt());
            ps.setBoolean(5, prestamo.isEstadoPt());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public boolean actualizarPrestamo(Prestamo prestamo) {
        boolean resultado = false;
        String query = "UPDATE Prestamo SET Numero_de_Cuenta_Cu_Pt = ?, Importe_solicitado_Pt = ?, Plazo_Pago_Pt = ?, Detalle_solicitud_Pt = ?, Estado_Pt = ? WHERE ID_Prestamo_Pt = ?";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, prestamo.getNumeroDeCuentaCuPt());
            ps.setBigDecimal(2, prestamo.getImporteSolicitadoPt());
            ps.setString(3, prestamo.getPlazoPagoPt());
            ps.setString(4, prestamo.getDetalleSolicitudPt());
            ps.setBoolean(5, prestamo.isEstadoPt());

            ps.setInt(6, prestamo.getIdPrestamoPt());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public boolean eliminarPrestamo(int idPrestamo) {
        boolean resultado = false;
        String query = "DELETE FROM Prestamo WHERE ID_Prestamo_Pt = ?";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, idPrestamo);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public List<Prestamo> obtenerPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Prestamo";

        try (Connection cn = conexion.Open(); Statement stmt = cn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamoPt(rs.getInt("ID_Prestamo_Pt"));
                prestamo.setNumeroDeCuentaCuPt(rs.getInt("Numero_de_Cuenta_Cu_Pt"));
                prestamo.setImporteSolicitadoPt(rs.getBigDecimal("Importe_solicitado_Pt"));
                prestamo.setPlazoPagoPt(rs.getString("Plazo_Pago_Pt"));
                prestamo.setDetalleSolicitudPt(rs.getString("Detalle_solicitud_Pt"));
                prestamo.setEstadoPt(rs.getBoolean("Estado_Pt"));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public Prestamo obtenerPrestamoPorId(int idPrestamo) {
        Prestamo prestamo = null;
        String query = "SELECT * FROM Prestamo WHERE ID_Prestamo_Pt = ?";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, idPrestamo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamoPt(rs.getInt("ID_Prestamo_Pt"));
                prestamo.setNumeroDeCuentaCuPt(rs.getInt("Numero_de_Cuenta_Cu_Pt"));
                prestamo.setImporteSolicitadoPt(rs.getBigDecimal("Importe_solicitado_Pt"));
                prestamo.setPlazoPagoPt(rs.getString("Plazo_Pago_Pt"));
                prestamo.setDetalleSolicitudPt(rs.getString("Detalle_solicitud_Pt"));
                prestamo.setEstadoPt(rs.getBoolean("Estado_Pt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }
    
   
    public boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, InteresesXCantidadDeMeses plazoPago, double montoPorCuota) {
        // Paso 1: Obtener la cuenta a partir de cuentaDestino
    	CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

    	// Llamar al método obtenerCuentaPorNumero utilizando la instancia
    	Cuenta cuenta = cuentaDao.obtenerCuentaPorNumero(cuentaDestino); // Este método debería existir en tu DAO para obtener la cuenta

        // Paso 2: Crear un objeto Prestamo
        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroDeCuentaCuPt(Integer.parseInt(cuentaDestino)); // Asumimos que cuentaDestino es un String que se puede convertir a int
        prestamo.setImporteSolicitadoPt(BigDecimal.valueOf(importeSolicitado)); 
        prestamo.setFechaPeticionPt(LocalDateTime.now()); // Fecha actual de solicitud
        prestamo.setPlazoPagoPt(plazoPago.getPlazoDPagosEnMesesIxm()); // Asumiendo que plazoPago es un número entero y lo convertimos a String
        prestamo.setDetalleSolicitudPt("Solicitud de préstamo de " + montoConInteres + " con plazo de " + plazoPago + " meses.");
        prestamo.setEstadoPt(true); // Asumimos que el préstamo está en estado activo al momento de la creación

        // Paso 3: Asociar la cuenta y el interés
        prestamo.setCuenta(cuenta); // Asumimos que ya tienes una clase Cuenta y un método para obtenerla por cuentaDestino
//        prestamo.setInteres(new InteresesXCantidadDeMesesDaoImpl(plazoPago, montoConInteres)); // Aquí debes tener una implementación para calcular los intereses según el plazo

        // Paso 4: Guardar el prestamo en la base de datos
        return insertarPrestamo(prestamo); // Llamamos al método insertarPrestamo para almacenar el préstamo en la base de datos
    }
    
    @Override
    public boolean comprobarPlazoExistente(String plazoPago) {
        boolean existe = false;
        String query = "SELECT COUNT(*) FROM interesxcantidaddmeses WHERE Plazo_d_Pagos_En_meses_IXM = ?";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, plazoPago);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {  // Verificamos si el valor existe
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }

}
