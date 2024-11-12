package negocio;

import daoImpl.ClienteDaoImpl;

public class NegocioClientes {
    
    private ClienteDaoImpl clienteDao;

    // Constructor para inicializar la clase DAO
    public NegocioClientes() {
        this.clienteDao = new ClienteDaoImpl();
    }

    // Método para dar de baja a un cliente de forma lógica
    public boolean eliminarCliente(String cuilCli) {
        // Validación antes de llamar al DAO (opcional)
        if (cuilCli == null || cuilCli.isEmpty()) {
            System.out.println("Error: CUIL inválido.");
            return false;
        }

        // Llama al método de la capa DAO para realizar la eliminación lógica
        boolean resultado = clienteDao.bajaDeCliente(cuilCli);

        

        return resultado;
    }
}
