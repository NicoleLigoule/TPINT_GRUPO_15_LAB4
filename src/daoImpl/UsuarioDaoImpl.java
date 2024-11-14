package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.UsuarioDao;
import entidades.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
    private Conexion cn;

    public UsuarioDaoImpl() {
        cn = new Conexion(); 
    }

    @Override
    public Usuario validarUsuario(String usuarioUs, String contraseniaUs) {
        Usuario usuario = null;
        cn.Open(); 

        String sql = "SELECT * FROM Usuario WHERE Usuario_us = ? AND Contrasenia_us = ?";
        try {
            PreparedStatement statement = cn.getSQLConexion().prepareStatement(sql);
            statement.setString(1, usuarioUs);
            statement.setString(2, contraseniaUs);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setCuilUs(resultSet.getString("Cuil_us"));
                usuario.setUsuarioUs(resultSet.getString("Usuario_us"));
                usuario.setContraseniaUs(resultSet.getString("Contrasenia_us"));
                usuario.setRolUs(resultSet.getBoolean("Rol_us"));
                usuario.setEstadoUs(resultSet.getBoolean("Estado_us"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); // Cierra la conexión después de usarla
        }
        return usuario;
    }
}

