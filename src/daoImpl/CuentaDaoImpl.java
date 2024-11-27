package daoImpl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CuentaDao;
import entidades.Cuenta;
import entidades.TipoDeCuenta;
import entidades.InteresesXCantidadDeMeses;

public class CuentaDaoImpl implements CuentaDao {

    private Conexion cn;
    private static final String readallTipoDeCuentas = "SELECT * FROM TipoCuenta";
    private static final String readallINteresesPrestamos = "SELECT Plazo_d_Pagos_En_meses_IXM,Interes_IXM,Meses_int FROM bancoutn.interesxcantidaddmeses;";
	public ArrayList<TipoDeCuenta> readallTipoDeCuentas()
	{
		//PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoDeCuenta> Tipos = new ArrayList<TipoDeCuenta>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			conexion.Open();			
			resultSet = conexion.query(readallTipoDeCuentas);
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
	        String query =
	            "SELECT C.Numero_de_Cuenta_Cu, C.Fecha_Creacion_Cu, T.Nombre_Tipo, C.CBU_Cu, C.Saldo_Cu, C.Estado_Cu, CC.Cuil_Cli_CC "+
	            "FROM Cuenta C "+
	            "JOIN TipoCuenta T ON C.Id_Tipo_Cuenta = T.Id_Tipo_Cuenta "+
	            "LEFT JOIN CuentasXCliente CC ON C.Numero_de_Cuenta_Cu = CC.Numero_de_Cuenta "+
	            "WHERE C.Estado_Cu = 1";
	        
	        ResultSet rs = cn.query(query);
	        while (rs.next()) {
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNumeroDeCuentaCu(rs.getInt("Numero_de_Cuenta_Cu"));
	            cuenta.setCuilCliCu(rs.getString("Cuil_Cli_CC")); // Obtiene el CUIL desde la tabla intermedia
	            cuenta.setFechaCreacionCu(rs.getDate("Fecha_Creacion_Cu").toLocalDate());
	            cuenta.setTipoCuentaDescripcion(rs.getString("Nombre_Tipo"));
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
    public boolean insertarSinCliente(Cuenta cuenta) {
        cn = new Conexion();
        cn.Open();
        boolean estado = true;
        String query = "INSERT INTO Cuenta (Fecha_Creacion_Cu, Id_Tipo_Cuenta, CBU_Cu, Estado_Cu) VALUES ('" +
                cuenta.getFechaCreacionCu() + "', " +
                cuenta.getIdTipoCuenta() + ", '" +
                cuenta.getCbuCu() + "', " +
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

    public ArrayList<InteresesXCantidadDeMeses> readallIntereses()
	{
		//PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<InteresesXCantidadDeMeses> Tipos = new ArrayList<InteresesXCantidadDeMeses>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			conexion.Open();			
			resultSet = conexion.query(readallINteresesPrestamos);
			while(resultSet.next())
			{
				Tipos.add(getINtereses(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return Tipos;
	}

	private InteresesXCantidadDeMeses getINtereses(ResultSet resultSet) throws SQLException
	{
		String plazoDPagosEnMesesIxm = resultSet.getString("Plazo_d_Pagos_En_meses_IXM");
		BigDecimal interesIxm = resultSet.getBigDecimal("Interes_IXM");
		int meses=resultSet.getInt("Meses_int");
		return new InteresesXCantidadDeMeses(plazoDPagosEnMesesIxm,interesIxm,meses);

	}
	
	public Cuenta obtenerCuentaPorNumero(String numeroCuenta) {
	    cn = new Conexion();
	    cn.Open();
	    Cuenta cuenta = null;
	    try {
	        String query = "SELECT * FROM Cuenta WHERE Estado_Cu = 1 AND Numero_de_Cuenta_Cu = ?";
	        PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
	        statement.setString(1, numeroCuenta);
	        ResultSet rs = statement.executeQuery();
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

	public List<Cuenta> obtenerCuentasReporte(int tipo, LocalDate fechaDesde, LocalDate fechaHasta) {
		
		cn = new Conexion();
        cn.Open();
        List<Cuenta> lista = new ArrayList<>();
        String query = "SELECT * FROM Cuenta WHERE "+
        			   "cuenta.Fecha_Creacion_Cu between ? AND ? "+
        			   "AND cuenta.Id_Tipo_Cuenta = ? ";
        
        try {
            PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
            statement.setDate(1, java.sql.Date.valueOf(fechaDesde)); // Fecha desde
            statement.setDate(2, java.sql.Date.valueOf(fechaHasta)); // Fecha hasta
            statement.setInt(3, tipo); // Tipo de cuenta
            
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

	public String obtenerCuilCuentaPorNumero(String numeroCuenta) {
	    cn = new Conexion();
	    cn.Open();
	    String N_cuil = null;
	    try {
	        String query = "SELECT Cuil_Cli_Cu  FROM bancoutn.Cuenta  WHERE Numero_de_Cuenta_Cu = ?";
	        PreparedStatement statement = cn.getSQLConexion().prepareStatement(query);
	        statement.setString(1, numeroCuenta);
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	        	N_cuil= (String) rs.getString("Cuil_Cli_Cu");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return N_cuil;
	}
}
