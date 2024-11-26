package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.ClienteCuentaDTO;
import negocio.DDL;
import negocio.NegocioCuentas;

/**
 * Servlet implementation class servletEliminarCuenta
 */
@WebServlet("/servletEliminarCuenta")
public class servletEliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEliminarCuenta() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LLEGA AL SERVLET DE CUENTA -GET-");
        String cuil = request.getParameter("cuil"); 

        if (cuil != null && !cuil.isEmpty()) {  
            DDL ddl = new DDL();
            try {
                
                ArrayList<ClienteCuentaDTO> listaCuentas = ddl.ClienteCuentas(cuil);

                if (listaCuentas != null && !listaCuentas.isEmpty()) {
                   
                    ClienteCuentaDTO cliente = listaCuentas.get(0); 
                    
                    String nombreApellido = cliente.getNombre() + " " + cliente.getApellido();

                    
                    request.setAttribute("nombreApellido", nombreApellido);
                    request.setAttribute("listaCuentas", listaCuentas);
                    
                    

                } else {
                    // Si no hay cuentas ni nombre, muestra un mensaje
                    request.setAttribute("mensaje", "No se encontraron clientes con CUIL: " + cuil);
                }
            } catch (Exception e) {
                e.printStackTrace();
                
                request.setAttribute("mensaje", "Error al obtener las cuentas: " + e.getMessage());
            }
        } 

       
        RequestDispatcher rd = request.getRequestDispatcher("EliminarCuenta.jsp");
        rd.forward(request, response);
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LLEGA AL SERVLET DE CUENTA - POST -");
		// Recupero el numero de cuenta
	    String numeroCuentaStr = request.getParameter("cuentasDelCliente");
	    String mensaje;
	    
	    if(numeroCuentaStr != null && !numeroCuentaStr.isEmpty()) 
	    {
	    	int numeroCuenta = Integer.parseInt(numeroCuentaStr);	    	
	    	System.out.println("N�mero de cuenta recibido: " + numeroCuenta);
	    	
	    	NegocioCuentas negocioCuentas = new NegocioCuentas();
	    	
	    	try 
	    	{
	    		Boolean eliminado = negocioCuentas.eliminarCuenta(numeroCuenta);
	    		if(eliminado) 
	    		{
	    			mensaje = "La cuenta ha sido eliminada exitosamente";
	    			System.out.println(mensaje);
	    		}
	    		else 
	    		{
	    			mensaje = "No se pudo eliminar la cuenta";
	    			System.out.println(mensaje);
	    		}
	    	}catch (Exception e) 
	    	{
	    		e.printStackTrace();
	            mensaje = "Error al eliminar la cuenta: " + e.getMessage();
			}
	    }else 
	    {
	    	mensaje = "No se ha seleccionado una cuenta para eliminar.";
	    }
	    
	    request.setAttribute("mensaje", mensaje);
	    RequestDispatcher rd = request.getRequestDispatcher("EliminarCuenta.jsp");
	    rd.forward(request, response);
	}

}
