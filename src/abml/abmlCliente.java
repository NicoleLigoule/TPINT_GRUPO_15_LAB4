package abml;

import java.util.List;

import daoImpl.ClienteDaoImpl;
import entidades.Cliente;

public class abmlCliente {
	private ClienteDaoImpl clienteDao; 
	
	
	public abmlCliente() {
		clienteDao = new ClienteDaoImpl();
	}
	
	public boolean agregarCliente(Cliente cliente) {
		return clienteDao.insertar(cliente);
	}
	
	public boolean editarCliente(Cliente cliente) {
		return clienteDao.editar(cliente);
	}
	
	public boolean eliminarCliente(int dni) {
		return clienteDao.borrar(dni);
	}
	
	public Cliente obtenerCliente(int dni) {
		return clienteDao.obtenerUno(dni);
	}
	
	public List<Cliente> obtenerClientesTodos() {
		return clienteDao.obtenerTodos();
	}
	
}
