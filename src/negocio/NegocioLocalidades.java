package negocio;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidades.Localidad;

public class NegocioLocalidades {
	private LocalidadDao daoLoc;
	
	public NegocioLocalidades() {
		daoLoc = new LocalidadDaoImpl();
	}
	
	public Localidad obtenerLocalidad(int idLoc) {
		return daoLoc.obtenerUna(idLoc);
	}
	
	
}
