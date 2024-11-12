package negocio;

import daoImpl.ClienteDaoImpl;

public class NegocioClientes {
    
    private ClienteDaoImpl clienteDao;

    // Constructor para inicializar la clase DAO
    public NegocioClientes() {
        this.clienteDao = new ClienteDaoImpl();
    }

    // M�todo para dar de baja a un cliente de forma l�gica
    public boolean eliminarCliente(String cuilCli) {
        // Validaci�n antes de llamar al DAO (opcional)
        if (cuilCli == null || cuilCli.isEmpty()) {
            System.out.println("Error: CUIL inv�lido.");
            return false;
        }

        // Llama al m�todo de la capa DAO para realizar la eliminaci�n l�gica
        boolean resultado = clienteDao.bajaDeCliente(cuilCli);

        

        return resultado;
    }
}
