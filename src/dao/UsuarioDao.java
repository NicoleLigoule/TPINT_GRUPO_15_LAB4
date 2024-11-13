package dao;

import entidades.Usuario;

public interface UsuarioDao {
    
    Usuario validarUsuario(String usuarioUs, String contraseniaUs);


}
