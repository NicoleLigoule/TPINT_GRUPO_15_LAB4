package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.LocalidadDao;
import entidades.Localidad;

public class LocalidadDaoImpl implements LocalidadDao {
    
    private Conexion cn;

    @Override
    public List<Localidad> obtenerTodas() {
        cn = new Conexion();
        cn.Open();
        List<Localidad> lista = new ArrayList<>();
        String query = "SELECT * FROM Localidad";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("ID_Localidad_Lca"));
                localidad.setNombreLoca(rs.getString("Nombre_Loc_Lca"));
                lista.add(localidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }

    @Override
    public Localidad obtenerUna(int id) {
        cn = new Conexion();
        cn.Open();
        Localidad localidad = null;
        String query = "SELECT * FROM Localidad WHERE ID_Localidad_Lca = " + id;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("ID_Localidad_Lca"));
                localidad.setNombreLoca(rs.getString("Nombre_Loc_Lca"));
                localidad.setId_provincia(rs.getInt("ID_Provincia_Prv_Lca"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return localidad;
    }

    @Override
    public List<Localidad> obtenerLocalidadesPorProvincia(int idProvincia) {
        cn = new Conexion();
        cn.Open();
        List<Localidad> lista = new ArrayList<>();
        String query = "SELECT * FROM Localidad WHERE ID_Provincia_Prv_Lca = " + idProvincia;
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("ID_Localidad_Lca"));
                localidad.setNombreLoca(rs.getString("Nombre_Loc_Lca"));
                lista.add(localidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }
    
    @Override
    public boolean insertar(Localidad localidad) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "INSERT INTO Localidad (Nombre_Loc_Lca) VALUES ('" + localidad.getNombreLoca() + "')";
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
    public boolean editar(Localidad localidad) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "UPDATE Localidad SET Nombre_Loc_Lca = '" + localidad.getNombreLoca() + "' WHERE ID_Localidad_Lca = " + localidad.getId_localidad();
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
        String query = "DELETE FROM Localidad WHERE ID_Localidad_Lca = " + id;
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
}
