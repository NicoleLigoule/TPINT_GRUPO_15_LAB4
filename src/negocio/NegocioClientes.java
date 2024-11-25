package negocio;

import java.util.List;

import daoImpl.ClienteDaoImpl;
import entidades.Cliente;
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
//    public int obtenProv(String lca) {
//    	ProvinciaDaoImpl dao=new ProvinciaDaoImpl();
//    	
//    	int prov= dao.ObtenerProvinciaXLocalidad(lca);
//    	return prov;
//    	
//    }
    
	public boolean agregarCliente(Cliente cliente) {
		return clienteDao.insertar(cliente);
	}
	
	public boolean editarCliente(Cliente cliente) {
		return clienteDao.editar(cliente);
	}
	
	
	public Cliente obtenerCliente(int dni) {
		return clienteDao.obtenerUno(dni);
	}
	
	public List<Cliente> obtenerClientesTodos() {
		return clienteDao.obtenerTodos();
	}
    
    
}

