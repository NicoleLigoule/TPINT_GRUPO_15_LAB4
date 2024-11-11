package daoImpl;

import dao.LocalidadDao;
import entidades.Localidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDaoImpl implements LocalidadDao {

    private Connection connection;

    public LocalidadDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Localidad> obtenerTodas() {
        List<Localidad> localidades = new ArrayList<>();
        String query = "SELECT ID_Localidad_Lca, ID_Provincia_Prv_Lca, Nombre_Loc_Lca FROM Localidad";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Localidad localidad = new Localidad(
                    rs.getInt("ID_Localidad_Lca"),
                    rs.getInt("ID_Provincia_Prv_Lca"),
                    rs.getString("Nombre_Loc_Lca")
                );
                localidades.add(localidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidades;
    }

    @Override
    public Localidad obtenerUna(int idLocalidad) {
        Localidad localidad = null;
        String query = "SELECT ID_Localidad_Lca, ID_Provincia_Prv_Lca, Nombre_Loc_Lca FROM Localidad WHERE ID_Localidad_Lca = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idLocalidad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    localidad = new Localidad(
                        rs.getInt("ID_Localidad_Lca"),
                        rs.getInt("ID_Provincia_Prv_Lca"),
                        rs.getString("Nombre_Loc_Lca")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidad;
    }

    @Override
    public boolean insertar(Localidad localidad) {
        String query = "INSERT INTO Localidad (ID_Provincia_Prv_Lca, Nombre_Loc_Lca) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, localidad.getId_provincia());
            ps.setString(2, localidad.getNombreLoca());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editar(Localidad localidad) {
        String query = "UPDATE Localidad SET ID_Provincia_Prv_Lca = ?, Nombre_Loc_Lca = ? WHERE ID_Localidad_Lca = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, localidad.getId_provincia());
            ps.setString(2, localidad.getNombreLoca());
            ps.setInt(3, localidad.getId_localidad());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean borrar(int idLocalidad) {
        String query = "DELETE FROM Localidad WHERE ID_Localidad_Lca = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idLocalidad);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
