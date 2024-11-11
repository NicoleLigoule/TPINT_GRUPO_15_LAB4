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
        String query = "SELECT * FROM localidades";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id"));
                localidad.setNombreLoca(rs.getString("nombre"));
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
        String query = "SELECT * FROM localidades WHERE id = " + id;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id"));
                localidad.setNombreLoca(rs.getString("nombre"));
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
        String query = "SELECT * FROM localidades WHERE id_provincia = " + idProvincia;
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Localidad localidad = new Localidad();
                localidad.setId_localidad(rs.getInt("id"));
                localidad.setNombreLoca(rs.getString("nombre"));
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
        String query = "INSERT INTO localidades (nombre) VALUES ('" + localidad.getNombreLoca() + "')";
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
        String query = "UPDATE localidades SET nombre = '" + localidad.getNombreLoca() + "' WHERE id = " + localidad.getId_localidad();
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
        String query = "DELETE FROM localidades WHERE id = " + id;
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
