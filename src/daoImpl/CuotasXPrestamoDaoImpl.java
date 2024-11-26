package daoImpl;

import dao.CuotasXPrestamoDao;
import entidades.CuotasXPrestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class CuotasXPrestamoDaoImpl implements CuotasXPrestamoDao {

    private Connection connection;

    public CuotasXPrestamoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregarCuota(CuotasXPrestamo cuota) {
        String sql = "INSERT INTO CuotasXPrestamos (ID_Prestamo_Pt_Cp, Fecha_vencimiento_Cp, N_Cuota) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cuota.getIdPrestamoPtCp());
            stmt.setDate(2, Date.valueOf(cuota.getFechaVencimientoCp()));
            stmt.setInt(3, cuota.getNCuota());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarCuota(CuotasXPrestamo cuota) {
        String sql = "UPDATE CuotasXPrestamos SET Fecha_vencimiento_Cp = ?, N_Cuota = ? WHERE ID_Prestamo_Pt_Cp = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(cuota.getFechaVencimientoCp()));
            stmt.setInt(2, cuota.getNCuota());
            stmt.setInt(3, cuota.getIdPrestamoPtCp());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCuota(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota) {
        String sql = "DELETE FROM CuotasXPrestamos WHERE ID_Prestamo_Pt_Cp = ? AND Fecha_vencimiento_Cp = ? AND N_Cuota = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPrestamoPtCp);
            stmt.setDate(2, Date.valueOf(fechaVencimientoCp));
            stmt.setInt(3, nCuota);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CuotasXPrestamo obtenerCuota(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota) {
        String sql = "SELECT * FROM CuotasXPrestamos WHERE ID_Prestamo_Pt_Cp = ? AND Fecha_vencimiento_Cp = ? AND N_Cuota = ?";
        CuotasXPrestamo cuota = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPrestamoPtCp);
            stmt.setDate(2, Date.valueOf(fechaVencimientoCp));
            stmt.setInt(3, nCuota);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cuota = new CuotasXPrestamo(
                        rs.getInt("ID_Prestamo_Pt_Cp"),
                        rs.getDate("Fecha_vencimiento_Cp").toLocalDate(),
                        rs.getInt("N_Cuota"),
                        rs.getBoolean("pagada")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuota;
    }

    @Override
    public List<CuotasXPrestamo> obtenerTodasLasCuotas() {
        String sql = "SELECT * FROM CuotasXPrestamos";
        List<CuotasXPrestamo> cuotas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CuotasXPrestamo cuota = new CuotasXPrestamo(
                        rs.getInt("ID_Prestamo_Pt_Cp"),
                        rs.getDate("Fecha_vencimiento_Cp").toLocalDate(),
                        rs.getInt("N_Cuota"),
                        rs.getBoolean("pagada")
                );
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }
    
    public void updateEstadoPago(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota, boolean estadoPago) throws SQLException {
        String sql = "UPDATE CuotasXPrestamos SET Estado_Pago = ? WHERE ID_Prestamo_Pt_Cp = ? AND Fecha_vencimiento_Cp = ? AND N_Cuota = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, estadoPago); // Manejo booleano
            stmt.setInt(2, idPrestamoPtCp);
            stmt.setDate(3, Date.valueOf(fechaVencimientoCp));
            stmt.setInt(4, nCuota);
            stmt.executeUpdate();
        }
    }
}
