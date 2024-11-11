package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
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
            ResultSet rs = cn.query("SELECT * FROM Cuenta WHERE Estado = 1");
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNumeroDeCuentaCu(rs.getInt("Numero"));
                cuenta.setCuilCliCu(rs.getString("ClienteCUIL"));
                cuenta.setFechaCreacionCu(rs.getDate("FechaCreacion").toLocalDate());
                cuenta.setIdTipoCuenta(rs.getInt("TipoCuentaID"));
                cuenta.setCbuCu(rs.getString("CBU"));
                cuenta.setSaldoCu(rs.getBigDecimal("Saldo"));
                cuenta.setEstadoCu(rs.getBoolean("Estado"));
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
            ResultSet rs = cn.query("SELECT * FROM Cuenta WHERE Estado = 1 AND Numero = " + numeroCuenta);
            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setNumeroDeCuentaCu(rs.getInt("Numero"));
                cuenta.setCuilCliCu(rs.getString("ClienteCUIL"));
                cuenta.setFechaCreacionCu(rs.getDate("FechaCreacion").toLocalDate());
                cuenta.setIdTipoCuenta(rs.getInt("TipoCuentaID"));
                cuenta.setCbuCu(rs.getString("CBU"));
                cuenta.setSaldoCu(rs.getBigDecimal("Saldo"));
                cuenta.setEstadoCu(rs.getBoolean("Estado"));
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return cuenta;
    }

    @Override
    public boolean insertar(Cuenta cuenta) {
        cn = new Conexion();
        cn.Open();
        boolean estado = true;
        String query = "INSERT INTO Cuenta (ClienteCUIL, FechaCreacion, TipoCuentaID, CBU, Saldo, Estado) VALUES ('" +
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
        String query = "UPDATE Cuenta SET ClienteCUIL = '" + cuenta.getCuilCliCu() +
                "', FechaCreacion = '" + cuenta.getFechaCreacionCu() +
                "', TipoCuentaID = " + cuenta.getIdTipoCuenta() +
                ", CBU = '" + cuenta.getCbuCu() +
                "', Saldo = " + cuenta.getSaldoCu() +
                ", Estado = " + (cuenta.isEstadoCu() ? 1 : 0) +
                " WHERE Numero = " + cuenta.getNumeroDeCuentaCu();
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
        String query = "UPDATE Cuenta SET Estado = 0 WHERE Numero = " + numeroCuenta;
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
