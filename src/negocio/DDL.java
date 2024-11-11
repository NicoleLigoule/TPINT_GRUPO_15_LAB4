package negocio;
import java.util.ArrayList;
import entidades.Nacionalidad;
import entidades.Sexo;
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
	 
	 public ArrayList<Sexo> Sexo(){
		 ddlClientes Sexo = new ddlClientes();
		 
		 ArrayList<Sexo> sex = Sexo.readallSexo(); 
		 return sex;
	 }
	 
}