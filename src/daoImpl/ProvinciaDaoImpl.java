package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.ProvinciaDao;
import entidades.Cuenta;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
    
    private Conexion cn;

    @Override
    public List<Provincia> obtenerTodas() {
        cn = new Conexion();
        cn.Open();
        List<Provincia> lista = new ArrayList<>();
        String query = "SELECT * FROM Provincia";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Provincia provincia = new Provincia();
                provincia.setId_provincia(rs.getInt("ID_Provincia_Prv"));
                provincia.setNombre(rs.getString("Nombre_Prov_Prv"));
                lista.add(provincia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }

    @Override
    public Provincia obtenerUna(int id) {
        cn = new Conexion();
        cn.Open();
        Provincia provincia = null;
        String query = "SELECT * FROM Provincia WHERE ID_Provincia_Prv = " + id;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                provincia = new Provincia();
                provincia.setId_provincia(rs.getInt("ID_Provincia_Prv"));
                provincia.setNombre(rs.getString("Nombre_Prov_Prv"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return provincia;
    }

    @Override
    public boolean insertar(Provincia provincia) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "INSERT INTO Provincia (Nombre_Prov_Prv) VALUES ('" + provincia.getNombre() + "')";
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }

    @Override
    public boolean editar(Provincia provincia) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "UPDATE Provincia SET Nombre_Prov_Prv = '" + provincia.getNombre() + "' WHERE ID_Provincia_Prv = " + provincia.getId_provincia();
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }

    @Override
    public boolean borrar(int id) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "DELETE FROM Provincia WHERE ID_Provincia_Prv = " + id;
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }
    public int ObtenerProvinciaXLocalidad(String localidad) {
    	int idProv=0;
        cn = new Conexion();
        cn.Open();
        String query = "SELECT ID_Provincia_Prv_Lca FROM Localidad WHERE ID_Localidad_Lca ="+localidad ;
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                idProv=rs.getInt("ID_Provincia_Prv_Lca");
               
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
    	
    	return idProv;
    }
    public double Porcentajes_p_aprobados() {
        cn = new Conexion();
        cn.Open();
        double porcentaje=0;
        try {
            ResultSet rs = cn.query("SELECT  (COUNT(CASE WHEN Estado_Pt = 1 THEN 1 END) * 100.0 / COUNT(*)) AS Porcentaje_Prestamos_Activos FROM  Prestamo;");
            if (rs.next()) {
             porcentaje = rs.getDouble("Porcentaje_Prestamos_Activos");
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return porcentaje;
    	
    }
    public double Rendimientos_p_aprobados() {
        cn = new Conexion();
        cn.Open();
        double resultado=0;
        try {
            ResultSet rs = cn.query("SELECT   (SUM(DetallesXPrestamo.Importe_C_Interes_Dt) - SUM(Prestamo.Importe_solicitado_Pt)) AS Resultado FROM Prestamo  JOIN DetallesXPrestamo  ON Prestamo.ID_Prestamo_Pt = DetallesXPrestamo.ID_Prestamo_Pt_Dt WHERE    Prestamo.Estado_Pt = 1 AND YEAR(Prestamo.Fecha_Peticion_Pt) = YEAR(CURDATE());");
            if (rs.next()) {
            	 resultado = rs.getDouble("Resultado");
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return resultado;
    	
    }
    public double RendimientosMEnsuales_p_aprobados() {
        cn = new Conexion();
        cn.Open();
        double resultado=0;
        try {
            ResultSet rs = cn.query("SELECT (SUM(DetallesXPrestamo.Importe_C_Interes_Dt) - SUM(Prestamo.Importe_solicitado_Pt)) AS Resultado FROM Prestamo  JOIN DetallesXPrestamo  ON Prestamo.ID_Prestamo_Pt = DetallesXPrestamo.ID_Prestamo_Pt_Dt WHERE Prestamo.Estado_Pt = 1   AND YEAR(Prestamo.Fecha_Peticion_Pt) = YEAR(CURDATE()) AND MONTH(Prestamo.Fecha_Peticion_Pt) = MONTH(CURDATE());");
            if (rs.next()) {
            	 resultado = rs.getDouble("Resultado");
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return resultado;
    	
    }
}
