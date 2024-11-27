package negocio;

import java.util.List;

import dao.UsuarioDao;
import daoImpl.ClienteDaoImpl;
import daoImpl.UsuarioDaoImpl;
import entidades.Cliente;
import entidades.Usuario;
public class NegocioClientes {
    
    private ClienteDaoImpl clienteDao;

    public NegocioClientes() {
        this.clienteDao = new ClienteDaoImpl();
    }

    public boolean eliminarCliente(String cuilCli) {
        if (cuilCli == null || cuilCli.isEmpty()) {
            System.out.println("Error: CUIL invalido.");
            return false;
        }

        return clienteDao.borrarCuil(cuilCli);
    }

    
	public boolean agregarCliente(Cliente cliente) {
		return clienteDao.insertar(cliente);
	}
	
	public boolean editarCliente(Cliente cliente) {
		return clienteDao.editar(cliente);
	}
	
	
	public Cliente obtenerCliente(int dni) {
		return clienteDao.obtenerUno(dni);
	}
	
	public Cliente obtenerClienteCuil(String cuil) {
		return clienteDao.obtenerUnoPorCuil(cuil);
	}
	
	public List<Cliente> obtenerClientesTodos() {
		return clienteDao.obtenerTodos();
	}
    
	public List<Cliente> obtenerClientesPorIdProvincia(int id) {
		return clienteDao.obtenerTodosPorProvincia(id);
	}
	
	public List<Cliente> obtenerClientesPorRangoEdades(int desde, int hasta) {
		return clienteDao.obtenerTodosPorEdades(desde, hasta);
	}
	
	public Usuario validadUsu(String nombre, String contrasenia) {
		UsuarioDao   usuarioDao = new UsuarioDaoImpl();
		Usuario usuario = usuarioDao.validarUsuario(nombre, contrasenia);
		return usuario;
	}


    
}

