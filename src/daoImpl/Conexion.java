package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/bancoutn";
    private static final String USER = "root"; // Reemplaza con tu usuario
    private static final String PASSWORD = "root"; // Reemplaza con tu contraseña
    private static Conexion instance;
    private Connection connection;

    private Conexion() {
    	  try {
              Class.forName("com.mysql.jdbc.Driver");
            //  this.connection = DriverManager.getConnection(host + dbName, user, pass);
          } catch (Exception e) {
              e.printStackTrace();
          }
        try {
            // Establece la conexión a la base de datos
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al establecer la conexión con la base de datos");
        }
    }

    public static Conexion getConexion() {
        if (instance == null) {
            instance = new Conexion();
        }
        return instance;
    }

    public Connection getSQLConexion() {
        return this.connection;
    }
}
