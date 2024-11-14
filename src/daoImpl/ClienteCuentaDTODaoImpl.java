package daoImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteCuentaDTODao;
import entidades.ClienteCuentaDTO;
import daoImpl.Conexion;

public class ClienteCuentaDTODaoImpl implements ClienteCuentaDTODao{

	@Override
	public List<ClienteCuentaDTO> obtenerCuentasPorCuil(String CUIL) {
		
		Conexion cn = new Conexion();
	    cn.Open();
	    List<ClienteCuentaDTO> lista = new ArrayList<>();
	    try {
	    	ResultSet rs = cn.query("SELECT " +
	    	        "Cliente.nombre_Clii AS Nombre, " +
	    	        "Cliente.apellido_Cli AS Apellido, " +
	    	        "Cuenta.Numero_de_Cuenta_Cu AS NumeroCuenta, " +
	    	        "TipoCuenta.Nombre_Tipo AS TipoCuenta " +
	    	        "FROM Cliente " +
	    	        "LEFT JOIN Cuenta ON Cliente.cuil_Cli = Cuenta.Cuil_Cli_Cu " +
	    	        "LEFT JOIN TipoCuenta ON Cuenta.Id_Tipo_Cuenta = TipoCuenta.Id_Tipo_Cuenta " +
	    	        "WHERE Cliente.cuil_Cli = '" + CUIL + "'");
	        
	        while (rs.next()) {
	            ClienteCuentaDTO dto = new ClienteCuentaDTO();
	            dto.setNombre(rs.getString("Nombre"));
	            dto.setApellido(rs.getString("Apellido"));
	            dto.setNumeroCuenta(rs.getInt("NumeroCuenta"));
	            dto.setTipoCuenta(rs.getString("TipoCuenta"));
	            lista.add(dto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {        	
	        cn.close();
	    }
	    return lista;
    	
	}
	
}
