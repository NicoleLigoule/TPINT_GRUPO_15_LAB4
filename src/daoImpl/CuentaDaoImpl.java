package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidades.ClienteCuentaDTO;
import entidades.Cuenta;
import entidades.TipoDeCuenta;

public class CuentaDaoImpl implements CuentaDao {

    private Conexion cn;
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
	
    @Override
    public List<Cuenta> obtenerTodos() {
        cn = new Conexion();
        cn.Open();
        List<Cuenta> lista = new ArrayList<>();
        try {
            ResultSet rs = cn.query("SELECT * FROM Cuenta WHERE Estado_Cu = 1");
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroDeCuentaCu(rs.getInt("Numero_de_Cuenta_Cu"));
                cuenta.setCuilCliCu(rs.getString("Cuil_Cli_Cu"));
                cuenta.setFechaCreacionCu(rs.getDate("Fecha_Creacion_Cu").toLocalDate());
                cuenta.setIdTipoCuenta(rs.getInt("Id_Tipo_Cuenta"));
                cuenta.setCbuCu(rs.getString("CBU_Cu"));
                cuenta.setSaldoCu(rs.getBigDecimal("Saldo_Cu"));
                cuenta.setEstadoCu(rs.getBoolean("Estado_Cu"));
                lista.add(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {        	
            cn.close();
        }
        return lista;
    }

    @Override
    public Cuenta obtenerUno(int numeroCuenta) {
        cn = new Conexion();
        cn.Open();
        Cuenta cuenta = null;
        try {
            ResultSet rs = cn.query("SELECT * FROM Cuenta WHERE Estado_Cu = 1 AND Numero_de_Cuenta_Cu = " + numeroCuenta);
            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setNumeroDeCuentaCu(rs.getInt("Numero_de_Cuenta_Cu"));
                cuenta.setCuilCliCu(rs.getString("Cuil_Cli_Cu"));
                cuenta.setFechaCreacionCu(rs.getDate("Fecha_Creacion_Cu").toLocalDate());
                cuenta.setIdTipoCuenta(rs.getInt("Id_Tipo_Cuenta"));
                cuenta.setCbuCu(rs.getString("CBU_Cu"));
                cuenta.setSaldoCu(rs.getBigDecimal("Saldo_Cu"));
                cuenta.setEstadoCu(rs.getBoolean("Estado_Cu"));
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return cuenta;
    }
    
    @Override
    public List<Cuenta> obtenerCuentasPorCuil(String cuil) {
        cn = new Conexion();
        cn.Open();
        List<Cuenta> lista = new ArrayList<>();
        String query = "SELECT * FROM Cuenta WHERE Estado_Cu = 1 AND Cuil_Cli_Cu = ?";
        
        try {
            PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
            statement.setString(1, cuil);
           ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroDeCuentaCu(rs.getInt("Numero_de_Cuenta_Cu"));
                cuenta.setCuilCliCu(rs.getString("Cuil_Cli_Cu"));
                cuenta.setFechaCreacionCu(rs.getDate("Fecha_Creacion_Cu").toLocalDate());
                cuenta.setIdTipoCuenta(rs.getInt("Id_Tipo_Cuenta"));
                cuenta.setCbuCu(rs.getString("CBU_Cu"));
                cuenta.setSaldoCu(rs.getBigDecimal("Saldo_Cu"));
                cuenta.setEstadoCu(rs.getBoolean("Estado_Cu"));
                lista.add(cuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return lista;
    }


    @Override
    public boolean insertar(Cuenta cuenta) {
        cn = new Conexion();
        cn.Open();
        boolean estado = true;
        String query = "INSERT INTO Cuenta (Cuil_Cli_Cu, Fecha_Creacion_Cu, Id_Tipo_Cuenta, CBU_Cu, Saldo_Cu, Estado_Cu) VALUES ('" +
                cuenta.getCuilCliCu() + "', '" +
                cuenta.getFechaCreacionCu() + "', " +
                cuenta.getIdTipoCuenta() + ", '" +
                cuenta.getCbuCu() + "', " +
                cuenta.getSaldoCu() + ", " +
                (cuenta.isEstadoCu() ? 1 : 0) + ")";
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            estado = false;
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    @Override
    public boolean editar(Cuenta cuenta) {
        cn = new Conexion();
        cn.Open();
        boolean estado = true;
        String query = "UPDATE Cuenta SET Cuil_Cli_Cu = '" + cuenta.getCuilCliCu() +
                "', Fecha_Creacion_Cu = '" + cuenta.getFechaCreacionCu() +
                "', Id_Tipo_Cuenta = " + cuenta.getIdTipoCuenta() +
                ", CBU_Cu = '" + cuenta.getCbuCu() +
                "', Saldo_Cu = " + cuenta.getSaldoCu() +
                ", Estado_Cu = " + (cuenta.isEstadoCu() ? 1 : 0) +
                " WHERE Numero_de_Cuenta_Cu = " + cuenta.getNumeroDeCuentaCu();
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            estado = false;
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    @Override
    public boolean borrar(int numeroCuenta) {
        cn = new Conexion();
        cn.Open();
        boolean estado = true;
        String query = "UPDATE Cuenta SET Estado_Cu = 0 WHERE Numero_de_Cuenta_Cu = " + numeroCuenta;
        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            estado = false;
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

	
    
}
