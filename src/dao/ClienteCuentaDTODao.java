package dao;

import java.util.List;

import entidades.ClienteCuentaDTO;

public interface ClienteCuentaDTODao {
	public abstract List<ClienteCuentaDTO> obtenerCuentasPorCuil(String CUIL);	
	public List<ClienteCuentaDTO> infoClienteCuentas2(String CUIL); 
}
