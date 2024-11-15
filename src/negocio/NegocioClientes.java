package negocio;

import daoImpl.ClienteDaoImpl;
import daoImpl.ProvinciaDaoImpl;
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
    public int obtenProv(String lca) {
    	ProvinciaDaoImpl dao=new ProvinciaDaoImpl();
    	
    	int prov= dao.ObtenerProvinciaXLocalidad(lca);
    	return prov;
    	
    }
}
