package daoImpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import daoImpl.Conexion;
import dao.ClienteDao;
import entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion cn;

    public ClienteDaoImpl() {}

    @Override
    public List<Cliente> obtenerTodos() {
        cn = new Conexion();
        cn.Open();
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCuil(rs.getString("cuil_Cli"));
                cliente.setDni(rs.getInt("dni_Cli"));
                cliente.setNombre(rs.getString("nombre_Clii"));
                cliente.setApellido(rs.getString("apellido_Cli"));
                cliente.setId_sexo(rs.getInt("ID_sexo_Cli"));
                cliente.setId_nacionalidad(rs.getString("ID_Nacionalidad_Cli"));
                cliente.setFechaNacimiento(rs.getObject("fecha_nacimiento_Cli", LocalDate.class));
                cliente.setDireccion(rs.getString("direccion_Cli"));
                cliente.setId_localidad(rs.getInt("ID_Localidad_cli"));
                cliente.setCorreo(rs.getString("correo_electronico_Cli"));
                cliente.setTelefono(rs.getString("telefono_Cli"));
                cliente.setEstado(rs.getBoolean("estado_Cli"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }

    @Override
    public Cliente obtenerUno(int dni) {
        cn = new Conexion();
        cn.Open();
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM Cliente WHERE dni_Cli = " + dni;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                cliente.setCuil(rs.getString("cuil_Cli"));
                cliente.setDni(rs.getInt("dni_Cli"));
                cliente.setNombre(rs.getString("nombre_Clii"));
                cliente.setApellido(rs.getString("apellido_Cli"));
                cliente.setId_sexo(rs.getInt("ID_sexo_Cli"));
                cliente.setId_nacionalidad(rs.getString("ID_Nacionalidad_Cli"));
                cliente.setFechaNacimiento(rs.getObject("fecha_nacimiento_Cli", LocalDate.class));
                cliente.setDireccion(rs.getString("direccion_Cli"));
                cliente.setId_localidad(rs.getInt("ID_Localidad_cli"));
                cliente.setCorreo(rs.getString("correo_electronico_Cli"));
                cliente.setTelefono(rs.getString("telefono_Cli"));
                cliente.setEstado(rs.getBoolean("estado_Cli"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return cliente;
    }

    @Override
    public boolean insertar(Cliente cliente) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "INSERT INTO Cliente (cuil_Cli, dni_Cli, nombre_Clii, apellido_Cli, ID_sexo_Cli, ID_Nacionalidad_Cli, fecha_nacimiento_Cli, direccion_Cli, ID_Localidad_cli, correo_electronico_Cli, telefono_Cli, estado_Cli) " +
                "VALUES ('" + cliente.getCuil() + "', " + cliente.getDni() + ", '" + cliente.getNombre() + "', '" + cliente.getApellido() + "', " + cliente.getId_sexo() + ", '" + cliente.getId_nacionalidad() + "', '" + cliente.getFechaNacimiento() + "', '" + cliente.getDireccion() + "', " + cliente.getId_localidad() + ", '" + cliente.getCorreo() + "', '" + cliente.getTelefono() + "', " + cliente.isEstado() + ")";
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
    public boolean editar(Cliente cliente) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "UPDATE Cliente SET cuil_Cli = '" + cliente.getCuil() + "', nombre_Clii = '" + cliente.getNombre() + "', apellido_Cli = '" + cliente.getApellido() + "', ID_sexo_Cli = " + cliente.getId_sexo() + ", ID_Nacionalidad_Cli = '" + cliente.getId_nacionalidad() + "', fecha_nacimiento_Cli = '" + cliente.getFechaNacimiento() + "', direccion_Cli = '" + cliente.getDireccion() + "', ID_Localidad_cli = " + cliente.getId_localidad() + ", correo_electronico_Cli = '" + cliente.getCorreo() + "', telefono_Cli = '" + cliente.getTelefono() + "', estado_Cli = " + cliente.isEstado() + " WHERE dni_Cli = " + cliente.getDni();
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
    public boolean borrar(int dni) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "UPDATE Cliente SET estado_Cli = 0 WHERE dni_Cli = " + dni;
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

    /*  Esta parte es del PR de Clau, TO-DO: Modificarlo para que implemente las clases del dao "borrar" */
  private static final String bajaDeCliente = "UPDATE cliente SET estado_Cli = 0 WHERE cuil_Cli = ?";

    public Boolean bajaDeCliente(String cuilCli) {
        PreparedStatement statement;
        int rowsUpdated = 0;

        Conexion conexion = Conexion.getConexion();
        try {
            statement = conexion.getSQLConexion().prepareStatement(bajaDeCliente);
            statement.setString(1, cuilCli);  // Asigna el valor de cuil_Cli como par�metro
            rowsUpdated = statement.executeUpdate(); // Ejecuta la actualizaci�n
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
        
        return rowsUpdated > 0; // Retorna true si al menos una fila fue actualizada
    }
    
}
