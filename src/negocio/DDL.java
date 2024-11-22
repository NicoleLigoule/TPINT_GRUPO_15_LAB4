package negocio;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.protocol.InfoOnlyServantCacheLocalCRDImpl;

import entidades.Nacionalidad;
import entidades.Sexo;
import entidades.TipoDeCuenta;
import entidades.Provincia;
import entidades.Localidad;
import entidades.Cliente;
import entidades.ClienteCuentaDTO;
import entidades.Cuenta;
import entidades.InteresesXCantidadDeMeses;
import daoImpl.CuentaDaoImpl;
import daoImpl.ClienteCuentaDTODaoImpl;
import daoImpl.ClienteDaoImpl;
import daoImpl.ddlClientes;
import daoImpl.ProvinciaDaoImpl;
import daoImpl.LocalidadDaoImpl;


public class DDL {
	
	 public ArrayList<Cliente> ClienteList(){
		 ClienteDaoImpl Cuenta= new ClienteDaoImpl();
		 
		 ArrayList<Cliente> Tipos = Cuenta.obtenerTodosarray() ; 
		 return Tipos;
	 }
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
	 
	 public ArrayList<Provincia> Provincia(){
		 ProvinciaDaoImpl Prov = new ProvinciaDaoImpl();
		 
		 ArrayList<Provincia> prov = (ArrayList<entidades.Provincia>) Prov.obtenerTodas(); 
		 return prov;
	 }
	 
	 public ArrayList<Localidad> Localidad(int idProvincia){
		 LocalidadDaoImpl Loc = new LocalidadDaoImpl();
		 
		 ArrayList<Localidad> loc = (ArrayList<entidades.Localidad>) Loc.obtenerLocalidadesPorProvincia(idProvincia); 
		 return loc;
	 }
	 
	 public ArrayList<ClienteCuentaDTO> ClienteCuentas(String CUIL) {
		    ClienteCuentaDTODaoImpl clienteCuenta = new ClienteCuentaDTODaoImpl();		    
		    return new ArrayList<ClienteCuentaDTO>(clienteCuenta.obtenerCuentasPorCuil(CUIL));
		}
	 
	 //	METODO PARA PROBAR TARJETAS DINAMICAS EN INFO-CLIENTE
	 public ArrayList<ClienteCuentaDTO> infoClienteCtas_2(String CUIL) {
		 	ClienteCuentaDTODaoImpl clienteCuenta = new ClienteCuentaDTODaoImpl();		    
		    return new ArrayList<ClienteCuentaDTO>(clienteCuenta.infoClienteCuentas2(CUIL));	 
	 }
	 
	 public List<Cuenta> obtenercuentaUsurio( String cuil) {
		 CuentaDaoImpl	 cuentaDao = new CuentaDaoImpl();
		 List<Cuenta> cuentas = cuentaDao.obtenerCuentasPorCuil(cuil);
		    return  cuentas;
		}
	 
	 public ArrayList<InteresesXCantidadDeMeses> obtenerIntereses() {
		 CuentaDaoImpl	 cuentaDao = new CuentaDaoImpl();
		 ArrayList<InteresesXCantidadDeMeses> INtereses = cuentaDao.readallIntereses();
		    return  INtereses;
		}
}