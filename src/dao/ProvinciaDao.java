package dao;

import java.util.List;
import entidades.Provincia;

public interface ProvinciaDao {
    public List<Provincia> obtenerTodas();
    public Provincia obtenerUna(int idProvincia);
    public boolean insertar(Provincia provincia);
    public boolean editar(Provincia provincia);
    public boolean borrar(int idProvincia);
}
