package excepciones;

public interface excepcionesPagoPrestamo {
	// void manejarExcepcion(Exception e);
	   void validarPrestamoExistente(int idPrestamo) throws Exception;
	   void validarCuentaExistente(int numeroCuenta) throws Exception;
	   void validarUsuarioAutenticado(Object usuario) throws Exception;  
}
