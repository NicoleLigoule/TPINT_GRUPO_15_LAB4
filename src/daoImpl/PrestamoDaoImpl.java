package daoImpl;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entidades.Cuenta;
import entidades.CuotasXPrestamo;
import entidades.DetalleXPrestamo;
import entidades.InteresesXCantidadDeMeses;
import entidades.Localidad;
import entidades.Prestamo;
import dao.PrestamoDao;


public class PrestamoDaoImpl implements PrestamoDao {
    private Conexion conexion;

    public PrestamoDaoImpl() {
        this.conexion = Conexion.getConexion();
    }

    @Override
    public int insertarPrestamo(Prestamo prestamo) {
        int idPrestamo = -1; // Valor por defecto si falla
        String query = "INSERT INTO Prestamo (Numero_de_Cuenta_Cu_Pt, Importe_solicitado_Pt, Plazo_Pago_Pt, Detalle_solicitud_Pt, Estado_Pt) VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prestamo.getNumeroDeCuentaCuPt());
            ps.setBigDecimal(2, prestamo.getImporteSolicitadoPt());
            ps.setString(3, prestamo.getPlazoPagoPt());
            ps.setString(4, prestamo.getDetalleSolicitudPt());
            ps.setBoolean(5, prestamo.isEstadoPt());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idPrestamo = generatedKeys.getInt(1); // Asumimos que el ID generado esta en la primera columna
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idPrestamo;
    }

    public interface PrestamoDao {
        List<Prestamo> obtenerPrestamosPorCuenta(int idCuenta);
    }


//    @Override
//    public boolean actualizarPrestamo(Prestamo prestamo) {
//        boolean resultado = false;
//        String query = "UPDATE Prestamo SET Numero_de_Cuenta_Cu_Pt = ?, Importe_solicitado_Pt = ?, Plazo_Pago_Pt = ?, Detalle_solicitud_Pt = ?, Estado_Pt = ? WHERE ID_Prestamo_Pt = ?";
//
//        try (Connection cn = conexion.Open()) {
//            PreparedStatement ps = cn.prepareStatement(query);
//            ps.setInt(1, prestamo.getNumeroDeCuentaCuPt());
//            ps.setBigDecimal(2, prestamo.getImporteSolicitadoPt());
//            ps.setString(3, prestamo.getPlazoPagoPt());
//            ps.setString(4, prestamo.getDetalleSolicitudPt());
//            ps.setBoolean(5, prestamo.isEstadoPt());
//
//            ps.setInt(6, prestamo.getIdPrestamoPt());
//
//            int filasAfectadas = ps.executeUpdate();
//            if (filasAfectadas > 0) {
//                resultado = true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultado;
//    }
    
    @Override
    public boolean actualizarPrestamo(Prestamo prestamo) {
        String query = "UPDATE Prestamo SET Estado_Pt = ? WHERE ID_Prestamo_Pt = ?";
        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);            
            ps.setBoolean(1, prestamo.isEstadoPt()); // Asumiendo que 'estado' es un booleano (pagado = true)
            ps.setInt(2, prestamo.getIdPrestamoPt());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        // query para traer el detalle del prestamo y las cuotas en el arraylist

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
    
    @Override
    public List<Prestamo> obtenerPrestamoPorCuenta(int numeroCuenta) {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT * FROM Prestamo WHERE Numero_de_Cuenta_Cu_Pt = ?";

        try (Connection cn = conexion.Open();
             PreparedStatement ps = cn.prepareStatement(query)) {

            ps.setInt(1, numeroCuenta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setIdPrestamoPt(rs.getInt("ID_Prestamo_Pt"));
                    prestamo.setNumeroDeCuentaCuPt(rs.getInt("Numero_de_Cuenta_Cu_Pt"));
                    prestamo.setFechaPeticionPt(rs.getDate("Fecha_Peticion_Pt").toLocalDate());
                    prestamo.setImporteSolicitadoPt(rs.getBigDecimal("Importe_solicitado_Pt"));
                    prestamo.setPlazoPagoPt(rs.getString("Plazo_Pago_Pt"));
                    prestamo.setDetalleSolicitudPt(rs.getString("Detalle_solicitud_Pt"));
                    prestamo.setEstadoPt(rs.getBoolean("Estado_Pt"));
                    prestamos.add(prestamo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    
    @Override
    public boolean guardarPrestamo(String cuentaDestino, double importeSolicitado, double montoConInteres, String plazoPago, String motivo) {
    	CuentaDaoImpl cuentaDao = new CuentaDaoImpl();

    	Cuenta cuenta = cuentaDao.obtenerCuentaPorNumero(cuentaDestino);

        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroDeCuentaCuPt(Integer.parseInt(cuentaDestino));
        prestamo.setImporteSolicitadoPt(BigDecimal.valueOf(importeSolicitado)); 
        prestamo.setFechaPeticionPt(LocalDate.now());
        prestamo.setPlazoPagoPt(plazoPago);
        prestamo.setDetalleSolicitudPt(motivo);
        prestamo.setEstadoPt(false);

        prestamo.setCuenta(cuenta); 
//        prestamo.setInteres(new InteresesXCantidadDeMesesDaoImpl(plazoPago, montoConInteres)); // tener una implementaci�n para calcular los intereses seg�n el plazo
        insertarPrestamo(prestamo); 
        
//        insertDetallePrestamo()
        return true;
    }
    
    @Override
    public boolean comprobarPlazoExistente(String plazoPago) {
        boolean existe = false;
        String query = "SELECT COUNT(*) FROM interesxcantidaddmeses WHERE Plazo_d_Pagos_En_meses_IXM = ?";

        try (Connection cn = conexion.Open()) {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, plazoPago);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return existe;
    }
    
    public ArrayList<CuotasXPrestamo> TraerCuotas(int idPrestamoPt){ 
        String queryCuotas = "SELECT Fecha_vencimiento_Cp, N_Cuota " +
                             "FROM CuotasXPrestamos WHERE ID_Prestamo_Pt_Cp = ?";
        
        ArrayList<CuotasXPrestamo> cuotasLocal = new ArrayList<>();

        try (Connection cn = conexion.Open();) {
        	PreparedStatement psCuotas = cn.prepareStatement(queryCuotas);
    		
    		
    		
    		
            psCuotas.setInt(1, idPrestamoPt);
    		
    		
            ResultSet rsCuotas = psCuotas.executeQuery();


               while (rsCuotas.next()) {
                   CuotasXPrestamo cuota = new CuotasXPrestamo();
                   cuota.setFechaVencimientoCp(rsCuotas.getDate("Fecha_vencimiento_Cp").toLocalDate());
                   cuota.setNCuota(rsCuotas.getInt("N_Cuota"));
                   cuotasLocal.add(cuota);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
        System.out.printf("cuotasLocal", cuotasLocal);

    	return cuotasLocal;
    }
    
    
    public DetalleXPrestamo TraerDetalles(int idPrestamoPt) {
        String queryDetalles = "SELECT Importe_C_Interes_Dt, Importe_X_Cuotas_Dt, Cantidad_Cuotas_Dt " +
                               "FROM DetallesXPrestamo WHERE ID_Prestamo_Pt_Dt = ?";

        DetalleXPrestamo detalleLocal = null;

        try {
            Connection cn = conexion.Open();
            PreparedStatement psDetalles = cn.prepareStatement(queryDetalles);
            psDetalles.setInt(1, idPrestamoPt);
            ResultSet rsDetalles = psDetalles.executeQuery();

            if (rsDetalles.next()) {
                detalleLocal = new DetalleXPrestamo();
                detalleLocal.setImporteCInteresDt(rsDetalles.getBigDecimal("Importe_C_Interes_Dt"));
                detalleLocal.setImporteXCuotasDt(rsDetalles.getBigDecimal("Importe_X_Cuotas_Dt"));
                detalleLocal.setCantidadCuotasDt(rsDetalles.getInt("Cantidad_Cuotas_Dt"));
            }
            rsDetalles.close();
            psDetalles.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("detalleLocal", detalleLocal);
        return detalleLocal;
    }
    public List<Prestamo> ListarPrestamosAprobar() {
        List<Prestamo> prestamos = new ArrayList<>();
        String query = "SELECT ID_Prestamo_Pt, Numero_de_Cuenta_Cu_Pt, Fecha_Peticion_Pt, Importe_solicitado_Pt, Plazo_Pago_Pt, Detalle_solicitud_Pt FROM bancoutn.prestamo WHERE Estado_Pt = 0;";

        try (Connection cn = conexion.Open(); Statement stmt = cn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamoPt(rs.getInt("ID_Prestamo_Pt"));
                prestamo.setNumeroDeCuentaCuPt(rs.getInt("Numero_de_Cuenta_Cu_Pt"));
                Date sqlDate = rs.getDate("Fecha_Peticion_Pt");
                if (sqlDate != null) {
                    prestamo.setFechaPeticionPt(sqlDate.toLocalDate());
                } else {
                    prestamo.setFechaPeticionPt(null); 
                }
                prestamo.setImporteSolicitadoPt(rs.getBigDecimal("Importe_solicitado_Pt"));
                prestamo.setPlazoPagoPt(rs.getString("Plazo_Pago_Pt"));
                prestamo.setDetalleSolicitudPt(rs.getString("Detalle_solicitud_Pt"));
                prestamo.setInteres(obtenerIntereses(prestamo.getPlazoPagoPt()));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }
    
    @Override
    public InteresesXCantidadDeMeses obtenerIntereses(String iDInteres) {
    	InteresesXCantidadDeMeses interes=new InteresesXCantidadDeMeses();
    	Conexion cn=new Conexion();
        cn.Open();
        try  {
	        String query = "SELECT Plazo_d_Pagos_En_meses_IXM, Interes_IXM, Meses_int  FROM bancoutn.InteresXCantidadDMeses WHERE Plazo_d_Pagos_En_meses_IXM = ?";
	        PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
	        statement.setString(1, iDInteres);
	        ResultSet rs = statement.executeQuery();
            if (rs.next()) {
              	interes.setPlazoDPagosEnMesesIxm(rs.getString("Plazo_d_Pagos_En_meses_IXM"));
            	interes.setInteresIxm(rs.getBigDecimal("Interes_IXM"));
                interes.setMeses(rs.getInt("Meses_int"));
                

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    	return interes;
    }
    
}

