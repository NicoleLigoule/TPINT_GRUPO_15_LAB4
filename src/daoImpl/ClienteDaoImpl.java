package daoImpl;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import daoImpl.Conexion;
import dao.ClienteDao;
import entidades.Cliente;

public class ClienteDaoImpl implements ClienteDao {

    private Conexion cn;

    public ClienteDaoImpl() {}

    @Override
    public List<Cliente> obtenerTodos() {
        cn = new Conexion();
        cn.Open();
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT * FROM clientes";
        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCuil(rs.getString("cuil"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setId_sexo(rs.getInt("id_sexo"));
                cliente.setId_nacionalidad(rs.getString("id_nacionalidad"));
                cliente.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setId_localidad(rs.getInt("id_localidad"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));
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
        String query = "SELECT * FROM clientes WHERE dni = " + dni;
        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                cliente.setCuil(rs.getString("cuil"));
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setId_sexo(rs.getInt("id_sexo"));
                cliente.setId_nacionalidad(rs.getString("id_nacionalidad"));
                cliente.setFechaNacimiento(rs.getObject("fechaNacimiento", LocalDate.class));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setId_localidad(rs.getInt("id_localidad"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setEstado(rs.getBoolean("estado"));
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
        String query = "INSERT INTO clientes (cuil, dni, nombre, apellido, id_sexo, id_nacionalidad, fechaNacimiento, direccion, id_localidad, correo, telefono, estado) " +
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
        String query = "UPDATE clientes SET cuil = '" + cliente.getCuil() + "', nombre = '" + cliente.getNombre() + "', apellido = '" + cliente.getApellido() + "', id_sexo = " + cliente.getId_sexo() + ", id_nacionalidad = '" + cliente.getId_nacionalidad() + "', fechaNacimiento = '" + cliente.getFechaNacimiento() + "', direccion = '" + cliente.getDireccion() + "', id_localidad = " + cliente.getId_localidad() + ", correo = '" + cliente.getCorreo() + "', telefono = '" + cliente.getTelefono() + "', estado = " + cliente.isEstado() + " WHERE dni = " + cliente.getDni();
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
        String query = "UPDATE clientes SET estado = 0 WHERE dni = " + dni;
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
