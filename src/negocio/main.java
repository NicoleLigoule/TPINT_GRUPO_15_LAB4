package negocio;
import java.util.ArrayList;

import entidades.TipoDeCuenta;
import entidades.Nacionalidad;
import negocio.DDL;
import negocio.NegocioClientes;
public class main {
	public static void main(String[] args) {
		NegocioClientes neg= new NegocioClientes();
		int h=neg.obtenProv("2");
		
		System.out.println("El ID de la provincia es: "+ h);
		
    DDL lista= new DDL();
    
    ArrayList<Nacionalidad> ver =lista.Nacionalidad();
    
    for (Nacionalidad cuenta : ver) {
        System.out.println(cuenta.toString());
    }
	}
}