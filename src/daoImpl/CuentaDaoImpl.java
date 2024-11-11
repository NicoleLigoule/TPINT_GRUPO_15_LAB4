package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidades.TipoDeCuenta;
public class CuentaDaoImpl {
	private static final String readallTipoDeCuentas = "SELECT Id_Tipo_Cuenta, Nombre_Tipo FROM bancoutn.tipocuenta";
	public ArrayList<TipoDeCuenta> readallTipoDeCuentas()
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoDeCuenta> Tipos = new ArrayList<TipoDeCuenta>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallTipoDeCuentas);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Tipos.add(getTpoSeguros(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Tipos;
	}
	
	private TipoDeCuenta getTpoSeguros(ResultSet resultSet) throws SQLException
	{
		int idTipo = resultSet.getInt("Id_Tipo_Cuenta");
		String Nombre = resultSet.getString("Nombre_Tipo");
		return new TipoDeCuenta(idTipo,Nombre);

	}
}