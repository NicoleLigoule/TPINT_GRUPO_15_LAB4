package daoImpl;

import dao.TransferenciaDao;
import entidades.Transferencias;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

public class TransferenciaDaoImpl implements TransferenciaDao {

	 private Conexion cn;
	
	 @Override
	 public void realizarTransferencia(int cuentaOrigen, int cuentaDestino, double monto, String detalle) throws Exception {
	     Conexion cn = Conexion.getConexion(); // Obtener la instancia de la conexión
	     cn.Open(); // Abrir la conexión a la base de datos

	     CallableStatement stmt = null;
	     try {
	         // Llamado al procedimiento almacenado RealizarTransferencia
	         String sql = "{CALL RealizarTransferencia(?, ?, ?, ?)}"; // Procedimiento almacenado

	         // Preparar el CallableStatement usando la conexión obtenida
	         stmt = cn.getSQLConexion().prepareCall(sql);

	         // Establecer los parámetros del procedimiento almacenado
	         stmt.setInt(1, cuentaOrigen);  // Cuenta de origen
	         stmt.setInt(2, cuentaDestino); // Cuenta de destino
	         stmt.setDouble(3, monto);     // Monto a transferir
	         stmt.setString(4, detalle);   // Detalle de la transferencia

	         // Ejecutar el procedimiento almacenado
	         stmt.execute();

	     } catch (SQLException e) {
	         // Manejo de errores
	         throw new Exception("Error al realizar la transferencia: " + e.getMessage(), e);
	     } finally {
	         // Cerrar la conexión
	         if (stmt != null) {
	             try {
	                 stmt.close();
	             } catch (SQLException e) {
	                 e.printStackTrace();
	             }
	         }
	         cn.close(); // Cerrar la conexión
	     }
	 }


}
