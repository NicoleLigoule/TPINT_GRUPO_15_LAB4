package daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDaoImpl {

    private static final String bajaDeCliente = "UPDATE cliente SET estado_Cli = 0 WHERE cuil_Cli = ?";

    public Boolean bajaDeCliente(String cuilCli) {
        PreparedStatement statement;
        int rowsUpdated = 0;

        Conexion conexion = Conexion.getConexion();
        try {
            statement = conexion.getSQLConexion().prepareStatement(bajaDeCliente);
            statement.setString(1, cuilCli);  // Asigna el valor de cuil_Cli como parámetro
            rowsUpdated = statement.executeUpdate(); // Ejecuta la actualización
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
        
        return rowsUpdated > 0; // Retorna true si al menos una fila fue actualizada
    }
}
