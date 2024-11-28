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

  /*  @Override
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
    }*/
    
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
   
}
