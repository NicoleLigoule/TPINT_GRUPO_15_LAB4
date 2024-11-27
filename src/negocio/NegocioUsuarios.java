package negocio;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidades.Usuario;

public class NegocioUsuarios {
	private UsuarioDao usrDao;
	
	public NegocioUsuarios(){
		usrDao = new UsuarioDaoImpl();
	}

	public Usuario validarUsuario(String usuarioUs, String contraseniaUs) {
		
		return usrDao.validarUsuario(usuarioUs, contraseniaUs);
	}
	
	
	
	
}
