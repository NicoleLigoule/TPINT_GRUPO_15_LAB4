package daoImpl;

import dao.ProvinciaDao;
import entidades.Provincia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDaoImpl implements ProvinciaDao {

    private Connection connection;

    public ProvinciaDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Provincia> obtenerTodas() {
        List<Provincia> provincias = new ArrayList<>();
        String query = "SELECT ID_Provincia_Prv, Nombre_Prov_Prv FROM Provincia";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Provincia provincia = new Provincia(
                    rs.getInt("ID_Provincia_Prv"),
                    rs.getString("Nombre_Prov_Prv")
                );
                provincias.add(provincia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provincias;
    }

    @Override
    public Provincia obtenerUna(int idProvincia) {
        Provincia provincia = null;
        String query = "SELECT ID_Provincia_Prv, Nombre_Prov_Prv FROM Provincia WHERE ID_Provincia_Prv = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idProvincia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    provincia = new Provincia(
                        rs.getInt("ID_Provincia_Prv"),
                        rs.getString("Nombre_Prov_Prv")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provincia;
    }

    @Override
    public boolean insertar(Provincia provincia) {
        String query = "INSERT INTO Provincia (Nombre_Prov_Prv) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, provincia.getNombre());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editar(Provincia provincia) {
        String query = "UPDATE Provincia SET Nombre_Prov_Prv = ? WHERE ID_Provincia_Prv = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, provincia.getNombre());
            ps.setInt(2, provincia.getId_provincia());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean borrar(int idProvincia) {
        String query = "DELETE FROM Provincia WHERE ID_Provincia_Prv = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idProvincia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
