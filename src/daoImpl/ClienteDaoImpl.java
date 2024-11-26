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
        String query = "SELECT c.cuil_Cli AS CUIL, c.dni_Cli AS DNI, c.nombre_Clii AS Nombre, c.apellido_Cli AS Apellido, "
                + "s.Descripcion AS Sexo, c.ID_Nacionalidad_Cli AS Nacionalidad, c.fecha_nacimiento_Cli AS FechaNacimiento, "
                + "c.direccion_Cli AS Direccion, l.Nombre_Loc_Lca AS Localidad, c.correo_electronico_Cli AS CorreoElectronico,"
                + " c.telefono_Cli AS Telefono, c.estado_Cli AS Estado FROM Cliente c INNER JOIN Sexo s ON "
                + "c.ID_sexo_Cli = s.ID_sexo_se INNER JOIN Localidad l ON c.ID_Localidad_Cli = l.ID_Localidad_Lca";
        
        try {
        	ResultSet rs = cn.query(query);
            while (rs.next()) {
                System.out.println("CUIL: " + rs.getString("CUIL"));
                System.out.println("DNI: " + rs.getInt("DNI"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Apellido: " + rs.getString("Apellido"));
                System.out.println("Sexo: " + rs.getString("Sexo"));
                System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
                System.out.println("Fecha Nacimiento: " + rs.getObject("FechaNacimiento", LocalDate.class));
                System.out.println("Dirección: " + rs.getString("Direccion"));
                System.out.println("Localidad: " + rs.getString("Localidad"));
                System.out.println("Correo: " + rs.getString("CorreoElectronico"));
                System.out.println("Teléfono: " + rs.getString("Telefono"));
                System.out.println("Estado: " + rs.getBoolean("Estado"));
                System.out.println("---------------------------------------------------");

                Cliente cliente = new Cliente();
                cliente.setCuil(rs.getString("CUIL"));
                cliente.setDni(rs.getInt("DNI"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setId_sexo(0); // No necesitas el ID si solo vas a usar el texto "Sexo"
                cliente.setDescripcion_sexo(rs.getString("Sexo")); // Asigna directamente la descripción del sexo
                cliente.setId_nacionalidad(rs.getString("Nacionalidad"));
                cliente.setFechaNacimiento(rs.getObject("FechaNacimiento", LocalDate.class));
                cliente.setDireccion(rs.getString("Direccion"));
                cliente.setId_localidad(0); // Similar, si solo necesitas la localidad por nombre
                cliente.setDescripcion_localidad(rs.getString("Localidad")); // Usa el nombre directamente
                cliente.setCorreo(rs.getString("CorreoElectronico"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setEstado(rs.getBoolean("Estado"));
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }
    
    public ArrayList<Cliente> obtenerTodosarray() {
        cn = new Conexion();
        cn.Open();
        ArrayList<Cliente> lista = new ArrayList<>();
        String query = "SELECT c.cuil_Cli AS CUIL, c.dni_Cli AS DNI, c.nombre_Clii AS Nombre, c.apellido_Cli AS Apellido, s.Descripcion AS Sexo, c.ID_Nacionalidad_Cli AS Nacionalidad, c.fecha_nacimiento_Cli AS FechaNacimiento, c.direccion_Cli AS Direccion, l.Nombre_Loc_Lca AS Localidad, c.correo_electronico_Cli AS CorreoElectronico, c.telefono_Cli AS Telefono, c.estado_Cli AS Estado FROM Cliente c INNER JOIN Sexo s ON c.ID_sexo_Cli = s.ID_sexo_se INNER JOIN Localidad l ON c.ID_Localidad_Cli = l.ID_Localidad_Lca";
        
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
    public Cliente obtenerUnoPorCuil(String cuil) {
        cn = new Conexion();
        cn.Open();
        Cliente cliente = new Cliente();
        String query = "SELECT * FROM Cliente WHERE cuil_Cli = '" + cuil + "'";
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
    
    @Override
    public boolean borrarCuil(String cuil) {
        boolean estado = true;
        cn = new Conexion();
        cn.Open();
        String query = "UPDATE Cliente SET estado_Cli = 0 WHERE cuil_Cli = ?";
        try {
            PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
            statement.setString(1, cuil);
            estado = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            estado = false;
        } finally {
            cn.close();
        }
        return estado;
    }
    
    public boolean eliminarCliente(String cuilCli) {
        if (cuilCli == null || cuilCli.isEmpty()) {
            System.out.println("Error: CUIL inválido.");
            return false;
        }
    ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    boolean borrarCuilCliente = clienteDao.borrarCuil(cuilCli);
    return borrarCuilCliente;
	}

	@Override
	public List<Cliente> obtenerTodosPorProvincia(int idProvincia) {
		
		cn = new Conexion();
        cn.Open();
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT cliente.cuil_Cli AS CUIL, cliente.dni_Cli AS DNI, cliente.nombre_Clii AS Nombre, " +
                "cliente.apellido_Cli AS Apellido, cliente.telefono_Cli AS Telefono, cliente.correo_electronico_Cli AS Correo, localidad.Nombre_Loc_Lca AS Localidad, provincia.Nombre_Prov_Prv AS Provincia " +
                "FROM cliente " +
                "INNER JOIN localidad ON cliente.ID_Localidad_cli = localidad.ID_Localidad_Lca " +
                "INNER JOIN provincia ON localidad.ID_Provincia_Prv_Lca = provincia.ID_Provincia_Prv " +
                "WHERE provincia.ID_Provincia_Prv = " + idProvincia + " AND cliente.estado_Cli = 1";
        
        try {
        	ResultSet rs = cn.query(query);
            while (rs.next()) {
                System.out.println("CUIL: " + rs.getString("CUIL"));
                System.out.println("DNI: " + rs.getInt("DNI"));
                System.out.println("Nombre: " + rs.getString("Nombre"));
                System.out.println("Apellido: " + rs.getString("Apellido"));
                System.out.println("Teléfono: " + rs.getString("Telefono"));
                System.out.println("Correo: " + rs.getString("Correo"));                                               
                System.out.println("Localidad: " + rs.getString("Localidad"));
                System.out.println("Provincia: " + rs.getString("Provincia"));               
                System.out.println("---------------------------------------------------");

                Cliente cliente = new Cliente();
                cliente.setCuil(rs.getString("CUIL"));
                cliente.setDni(rs.getInt("DNI"));
                cliente.setNombre(rs.getString("Nombre"));
                cliente.setApellido(rs.getString("Apellido"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setCorreo(rs.getString("Correo"));                
                cliente.setDescripcion_localidad(rs.getString("Localidad")); // Usa el nombre directamente
                cliente.setDescripcion_provincia(rs.getString("Provincia"));
                
                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
		
	}


    /*  Esta parte es del PR de Clau, TO-DO: Modificarlo para que implemente las clases del dao "borrar" */
 /* private static final String bajaDeCliente = "UPDATE cliente SET estado_Cli = 0 WHERE cuil_Cli = ?";

    public Boolean bajaDeCliente(String cuilCli) {
        PreparedStatement statement;
        int rowsUpdated = 0;

        Conexion conexion = Conexion.getConexion();
        try {
            statement = conexion.getSQLConexion().prepareStatement(bajaDeCliente);
            statement.setString(1, cuilCli);  // Asigna el valor de cuil_Cli como parï¿½metro
            rowsUpdated = statement.executeUpdate(); // Ejecuta la actualizaciï¿½n
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false en caso de error
        }
        
        return rowsUpdated > 0; // Retorna true si al menos una fila fue actualizada
    }*/
    
}
