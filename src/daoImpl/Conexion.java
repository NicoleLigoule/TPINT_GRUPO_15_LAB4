package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/bancoutn";
    private static final String USER = "root"; 
    private static final String PASSWORD = "root"; 
    private static Conexion instance;
    private Connection connection;

    Conexion() {
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error al establecer la conexión con la base de datos");
        }
    }

    public Connection Open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al establecer la conexión con la base de datos");
        }
        return this.connection;
    }


    public ResultSet query(String query) {
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean execute(String query) {
        Statement st;
        boolean save = true;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            save = false;
            e.printStackTrace();
        }
        return save;
    }

    public boolean close() {
        boolean ok = true;
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        return ok;
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
