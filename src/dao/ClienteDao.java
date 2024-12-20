package dao;

import java.util.List;
import entidades.Cliente;

public interface ClienteDao {
    public List<Cliente> obtenerTodos();
    public Cliente obtenerUno(int dni);
    public boolean insertar(Cliente cliente);
    public boolean editar(Cliente cliente);
    public boolean borrar(int dni);
    public boolean borrarCuil(String cuil);
	public Cliente obtenerUnoPorCuil(String cuil);    
	public List<Cliente> obtenerTodosPorProvincia(int idProvincia);
	public List<Cliente> obtenerTodosPorEdades(int desde, int hasta);
}