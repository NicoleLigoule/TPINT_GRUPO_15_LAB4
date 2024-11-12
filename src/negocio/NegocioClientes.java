package negocio;

import daoImpl.ClienteDaoImpl;

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
}
