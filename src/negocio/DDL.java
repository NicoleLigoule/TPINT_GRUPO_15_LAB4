package negocio;
import java.util.ArrayList;
import entidades.TipoDeCuenta;
import daoImpl.CuentaDaoImpl;
public class DDL {
	
	 public ArrayList<TipoDeCuenta> TipoDecuenta(){
		 CuentaDaoImpl Cuenta= new CuentaDaoImpl();
		 
		 ArrayList<TipoDeCuenta> Tipos = Cuenta.readallTipoDeCuentas() ; 
		 return Tipos;
	 }
}