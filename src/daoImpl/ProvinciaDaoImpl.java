package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.ProvinciaDao;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
    
    private Conexion cn;

    @Override
    public List<Provincia> obtenerTodas() {
        cn = new Conexion();
        cn.Open();
        List<Provincia> lista = new ArrayList<>();
        String query = "SELECT * FROM provincias";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Provincia provincia = new Provincia();
                provincia.setId_provincia(rs.getInt("id"));
                provincia.setNombre(rs.getString("nombre"));
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
        String query = "SELECT * FROM provincias WHERE id = " + id;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                provincia = new Provincia();
                provincia.setId_provincia(rs.getInt("id"));
                provincia.setNombre(rs.getString("nombre"));
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
        String query = "INSERT INTO provincias (nombre) VALUES ('" + provincia.getNombre() + "')";
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
        String query = "UPDATE provincias SET nombre = '" + provincia.getNombre() + "' WHERE id = " + provincia.getId_provincia();
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
        String query = "DELETE FROM provincias WHERE id = " + id;
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
