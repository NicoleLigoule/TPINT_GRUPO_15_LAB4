package daoImpl;

import java.sql.ResultSet;

import entidades.InteresesXCantidadDeMeses;

public class InteresesXCantidadDeMesesDaoImpl implements dao.InteresesXCantidadDeMesesDao {

	public InteresesXCantidadDeMesesDaoImpl() {
	
	}
	
	public InteresesXCantidadDeMeses obtenerUno(String plazo) {
		Conexion cn = new Conexion();
        cn.Open();
		InteresesXCantidadDeMeses interes = null;
		
		String query = "SELECT * FROM interesxcantidaddmeses WHERE Plazo_d_Pagos_En_meses_IXM = '" + plazo + "'";
		
		try {
			
			ResultSet rs = cn.query(query);
			if(rs.next()) {
				interes = new InteresesXCantidadDeMeses();
				
				interes.setPlazoDPagosEnMesesIxm(rs.getString("Plazo_d_Pagos_En_meses_IXM"));				
				interes.setInteresIxm(rs.getBigDecimal("Interes_IXM"));
				interes.setMeses(rs.getInt("Meses"));
			}

			
		}catch(Exception e) {
            e.printStackTrace();
        } finally {
        	cn.close();
        }
		
		return interes;
	}

}
