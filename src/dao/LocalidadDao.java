package dao;

import java.util.List;
import entidades.Localidad;

public interface LocalidadDao {
    public List<Localidad> obtenerTodas();
    public Localidad obtenerUna(int idLocalidad);
    public boolean insertar(Localidad localidad);
    public boolean editar(Localidad localidad);
    public boolean borrar(int idLocalidad);
}
