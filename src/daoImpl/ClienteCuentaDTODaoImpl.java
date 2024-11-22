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
	    	        "LEFT JOIN Cuenta ON Cliente.cuil_Cli = Cuenta.Cuil_Cli_Cu AND Cuenta.Estado_Cu = 1 " +
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

	@Override
	public ClienteCuentaDTO infoClienteCuentas(String CUIL) {
		
		Conexion cn = new Conexion();
	    cn.Open();
	    ClienteCuentaDTO infoCliente = new ClienteCuentaDTO();
	    try {
	    	ResultSet rs = cn.query("SELECT " +
	    	        "Cliente.nombre_Clii AS Nombre, " +
	    	        "Cliente.apellido_Cli AS Apellido, " +
	    	        "Cliente.correo_electronico_Cli AS Correo, "+
	    	        "Cliente.telefono_Cli AS Telefono, "+
	    	        "Cliente.direccion_Cli AS Direccion, "+
	    	        "cuenta.CBU_Cu AS CBU, "+
	    	        "MAX(CASE "+ 
	    	                "WHEN Cuenta.Id_Tipo_Cuenta = 2 THEN Cuenta.Saldo_Cu "+
	    	                "ELSE 0 "+ 
	    	            "END) AS Saldo_Cuenta_Corriente, "+
	    	           "MAX(CASE "+
	    	                "WHEN Cuenta.Id_Tipo_Cuenta = 1 THEN Cuenta.Saldo_Cu "+
	    	                "ELSE 0 "+ 
	    	            "END) AS Saldo_Caja_Ahorro, "+
	    	         "MAX(CASE "+
	    	                    "WHEN Cuenta.Id_Tipo_Cuenta = 2 THEN Cuenta.Numero_de_Cuenta_Cu "+
	    	                    "ELSE NULL "+ 
	    	                "END) AS Numero_Cuenta_Corriente, "+
	    	          "MAX(CASE "+
	    	                    "WHEN Cuenta.Id_Tipo_Cuenta = 1 THEN Cuenta.Numero_de_Cuenta_Cu "+
	    	                    "ELSE NULL "+ 
	    	                    "END) AS Numero_Cuenta_Caja_Ahorro, "+
	    	        "Cuenta.Numero_de_Cuenta_Cu AS NumeroCuenta, " +
	    	        "TipoCuenta.Nombre_Tipo AS TipoCuenta " +
	    	        "FROM Cliente " +
	    	        "LEFT JOIN Cuenta ON Cliente.cuil_Cli = Cuenta.Cuil_Cli_Cu AND Cuenta.Estado_Cu = 1 " +
	    	        "LEFT JOIN TipoCuenta ON Cuenta.Id_Tipo_Cuenta = TipoCuenta.Id_Tipo_Cuenta " +
	    	        "WHERE Cliente.cuil_Cli = '" + CUIL + "' "+
	    	        "GROUP BY "+ 
	    	        "Cliente.nombre_Clii, Cliente.correo_electronico_Cli, "+
	    	        "Cliente.telefono_Cli, Cliente.direccion_Cli ");
	    	        
	    			
	        
	        if (rs.next()) {
	        	infoCliente.setNombre(rs.getString("Nombre"));
	            infoCliente.setApellido(rs.getString("Apellido"));
	            infoCliente.setCorreoElectronico(rs.getString("Correo"));
	            infoCliente.setTelefono(rs.getString("Telefono"));
	            infoCliente.setDireccion(rs.getString("Direccion"));
	            infoCliente.setNumeroCuenta(rs.getInt("NumeroCuenta"));
	            infoCliente.setTipoCuenta(rs.getString("TipoCuenta"));
	            infoCliente.setSaldoCtaCorriente(rs.getBigDecimal("Saldo_Cuenta_Corriente"));
	            infoCliente.setSaldoCajaAhorro(rs.getBigDecimal("Saldo_Caja_Ahorro"));
	            infoCliente.setNumeroCuentaCorriente(rs.getInt("Numero_Cuenta_Corriente"));
	            infoCliente.setNumeroCajaAhorro(rs.getInt("Numero_Cuenta_Caja_Ahorro"));
	            
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {        	
	        cn.close();
	    }
	    return infoCliente;
	}
	
}
