package negocio;

import java.util.ArrayList;

import entidades.TipoDeCuenta;
import negocio.DDL;
public class main {

	public static void main(String[] args) {
    DDL lista= new DDL();
    
    ArrayList<TipoDeCuenta> ver =lista.TipoDecuenta();
    
    for (TipoDeCuenta cuenta : ver) {
        System.out.println(cuenta.toString());
    }
	}

}
