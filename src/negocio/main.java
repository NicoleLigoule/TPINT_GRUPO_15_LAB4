package negocio;
import java.util.ArrayList;
import java.util.List;

import entidades.TipoDeCuenta;
import entidades.Nacionalidad;
import entidades.Prestamo;
import negocio.DDL;
import negocio.NegocioClientes;
public class main {
	public static void main(String[] args) {

		
    DDL lista= new DDL();
    
    List<Prestamo> listaPrestamo = lista.ListarPrestamosAprobar();
    
    for (Prestamo cuenta : listaPrestamo) {
        System.out.println(cuenta.toString());
    }
	}
}