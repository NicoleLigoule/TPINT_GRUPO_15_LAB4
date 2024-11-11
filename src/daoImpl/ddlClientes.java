package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Nacionalidad;
import entidades.Sexo;


public class ddlClientes {
	private static final String readallNAcionalidad = "SELECT Id_Nacionalidad_nc, Descripcion_nc FROM bancoutn.nacionalidad;";
	public ArrayList<Nacionalidad> readallNacionalidad()
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Nacionalidad> Tipos = new ArrayList<Nacionalidad>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallNAcionalidad);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Tipos.add(getNacionalidad(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Tipos;
	}
	
	private Nacionalidad getNacionalidad(ResultSet resultSet) throws SQLException
	{
		String id = resultSet.getString("Id_Nacionalidad_nc");
		String Descripcion = resultSet.getString("Descripcion_nc");
		return new Nacionalidad(id,Descripcion);

	}
	
	private static final String readallSexo = "SELECT ID_sexo_se, Descripcion FROM bancoutn.sexo;";

	public ArrayList<Sexo> readallSexo() {
	    PreparedStatement statement;
	    ResultSet resultSet;
	    ArrayList<Sexo> item = new ArrayList<Sexo>();
	    Conexion conexion = Conexion.getConexion();
	    try {
	        statement = conexion.getSQLConexion().prepareStatement(readallSexo);
	        resultSet = statement.executeQuery();
	        while(resultSet.next()) {
	            item.add(getSexo(resultSet)); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return item;
	}


	private Sexo getSexo(ResultSet resultSet) throws SQLException {
	    int id = resultSet.getInt("ID_sexo_se"); 
	    String descripcion = resultSet.getString("Descripcion");
	    return new Sexo(id, descripcion); 
	}

}
