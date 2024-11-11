package negocio;
import java.util.ArrayList;
import entidades.TipoDeCuenta;
import entidades.Nacionalidad;
import negocio.DDL;
public class main {
	public static void main(String[] args) {
    DDL lista= new DDL();
    
    ArrayList<Nacionalidad> ver =lista.Nacionalidad();
    
    for (Nacionalidad cuenta : ver) {
        System.out.println(cuenta.toString());
    }
	}
}