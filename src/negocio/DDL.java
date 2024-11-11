package negocio;
import java.util.ArrayList;
import entidades.Nacionalidad;
import entidades.TipoDeCuenta;
import daoImpl.CuentaDaoImpl;
import daoImpl.ddlClientes;
public class DDL {
	
	 public ArrayList<TipoDeCuenta> TipoDecuenta(){
		 CuentaDaoImpl Cuenta= new CuentaDaoImpl();
		 
		 ArrayList<TipoDeCuenta> Tipos = Cuenta.readallTipoDeCuentas() ; 
		 return Tipos;
	 }
	 public ArrayList<Nacionalidad> Nacionalidad(){
		 ddlClientes nacionalidad= new ddlClientes();
		 
		 ArrayList<Nacionalidad> nac = nacionalidad.readallNacionalidad(); 
		 return nac;
	 }
}