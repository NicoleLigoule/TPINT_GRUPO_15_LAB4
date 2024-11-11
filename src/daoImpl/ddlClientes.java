package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.Nacionalidad;


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
}
