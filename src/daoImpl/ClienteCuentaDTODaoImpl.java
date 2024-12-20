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
	public List<ClienteCuentaDTO> infoClienteCuentas2(String CUIL) {
		
		Conexion cn = new Conexion();
	    cn.Open();
	    List<ClienteCuentaDTO> lista = new ArrayList<>();
	    try {
	    	ResultSet rs = cn.query("SELECT  cliente.nombre_Clii AS Nombre, cliente.apellido_Cli AS Apellido, cliente.dni_Cli AS DNI, cliente.cuil_Cli AS CUIL, cliente.fecha_nacimiento_Cli as 'Fecha de Nacimiento', nacionalidad.Descripcion_nc AS Nacionalidad ,cliente.correo_electronico_Cli AS Correo, cliente.telefono_Cli AS telefono, cliente.direccion_Cli AS Direccion,localidad.Nombre_Loc_Lca AS Localidad, provincia.Nombre_Prov_Prv AS Provincia, tipocuenta.Nombre_Tipo AS Tipo, cuenta.Numero_de_Cuenta_Cu AS NumeroCuenta, cuenta.Saldo_Cu AS Saldo " + 
	    			"FROM cliente LEFT JOIN  "+
	    			"cuenta ON cliente.cuil_Cli = cuenta.Cuil_Cli_Cu AND cuenta.Estado_Cu = 1 "+
	    			"INNER JOIN localidad ON localidad.ID_Localidad_Lca = cliente.ID_Localidad_cli "+
	    			"INNER JOIN provincia ON localidad.ID_Provincia_Prv_Lca = provincia.ID_Provincia_Prv "+
	    			"INNER JOIN nacionalidad ON cliente.ID_Nacionalidad_Cli = nacionalidad.Id_Nacionalidad_nc "+
	    			"LEFT JOIN "+ 
	    			"tipocuenta ON cuenta.Id_Tipo_Cuenta = tipocuenta.Id_Tipo_Cuenta "+
	    	        "WHERE Cliente.cuil_Cli = '" + CUIL + "'");
	        
	        while (rs.next()) {
	        	 System.out.println("Datos obtenidos del ResultSet:");
	             System.out.println("Nombre: " + rs.getString("Nombre"));
	             System.out.println("Apellido: " + rs.getString("Apellido"));
	             System.out.println("Dni: " + rs.getString("DNI"));
	             System.out.println("Cuil: " + rs.getString("CUIL"));
	             System.out.println("Fecha: " + rs.getString("Fecha de Nacimiento"));
	             System.out.println("Nacionalidad: " + rs.getString("Nacionalidad"));
	             System.out.println("Correo: " + rs.getString("Correo"));
	             System.out.println("Telefono: " + rs.getString("telefono"));
	             System.out.println("Direccion: " + rs.getString("Direccion"));
	             System.out.println("Localidad: " + rs.getString("Localidad"));
	             System.out.println("Provincia: " + rs.getString("Provincia"));
	             System.out.println("Cuenta: " + rs.getString("Tipo"));
	             System.out.println("N�mero de Cuenta: " + rs.getInt("NumeroCuenta"));
	             System.out.println("Saldo: " + rs.getBigDecimal("Saldo"));
	        	
	            ClienteCuentaDTO dto = new ClienteCuentaDTO();
	            dto.setNombre(rs.getString("Nombre"));
	            dto.setApellido(rs.getString("Apellido"));
	            dto.setDni(rs.getString("DNI"));
	            dto.setCuil(rs.getString("CUIL"));
	            dto.setFechaNacimiento(rs.getString("Fecha de Nacimiento"));
	            dto.setNacionalidad(rs.getString("Nacionalidad"));
	            dto.setCorreoElectronico(rs.getString("Correo"));
	            dto.setTelefono(rs.getString("telefono"));	            
	            dto.setDireccion(rs.getString("Direccion"));
	            dto.setLocalidadDescr(rs.getString("Localidad"));
	            dto.setProvinciaDescr(rs.getString("Provincia"));
	            dto.setTipoCuenta(rs.getString("Tipo"));
	            dto.setNumeroCuenta(rs.getInt("NumeroCuenta"));            
	            dto.setSaldo(rs.getBigDecimal("Saldo"));
	            
	            
	            
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
