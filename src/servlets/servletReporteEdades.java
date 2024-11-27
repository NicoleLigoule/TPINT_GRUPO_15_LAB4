package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.NegocioClientes;

/**
 * Servlet implementation class servletReporteEdades
 */
@WebServlet("/servletReporteEdades")
public class servletReporteEdades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReporteEdades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("LLEGA AL SERVLET - get");
		
		int desdeNum = 0;
		int hastaNum = 0;
		String desde = request.getParameter("fecha-desde");
	    System.out.println("Valor recibido : " + desde);
	    String hasta = request.getParameter("fecha-hasta");
	    System.out.println("Valor recibido : " + hasta);
		
		if (request.getParameter("mostrarBtn") != null) 
		{
			if (desde != null && !desde.isEmpty()) {
		        try {
		        	desdeNum = Integer.parseInt(desde);
		            System.out.println("Desde: " + desdeNum);
		            hastaNum = Integer.parseInt(hasta);
		            System.out.println("Desde: " + hastaNum);
		        } catch (NumberFormatException e) {
		            System.out.println("Error: El valor recibido no es un número válido: " + desde);
		            System.out.println("Error: El valor recibido no es un número válido: " + hasta);
		        }
		    } else {
		        System.out.println("No se recibió el parámetro o está vacío.");
		    }
			NegocioClientes negocioClientes = new NegocioClientes();
			 try
			    {
			    	System.out.print("entra a lista");
			    	List<Cliente> clientesPorEdades = negocioClientes.obtenerClientesPorRangoEdades(desdeNum, hastaNum);
			    	System.out.println("Resultado obtenido: " + clientesPorEdades);
			    	if (clientesPorEdades != null && !clientesPorEdades.isEmpty()) 
			    	{
			    		System.out.println("Contenido de clientesPorEdades:");
	                    for (Object cli : clientesPorEdades) 
	                    {
	                        System.out.println(cli);
	                    }
	                    request.setAttribute("clientesPorEdades", clientesPorEdades); 
	                    
	                    int cantidadResultados = clientesPorEdades.size();
	                    request.setAttribute("cantidadResultados", cantidadResultados);
	                    
			    	} else {
	                    request.setAttribute("Mensaje", "No se encontraron clientes.");                
			    	}
			    	
			    	RequestDispatcher rd = request.getRequestDispatcher("ReporteEdades.jsp");
			         rd.forward(request, response);
				} 
			    catch (Exception e) 
			    {
			    	e.printStackTrace();response.getWriter().println("Error al obtener la lista de clientes: " + e.getMessage());
				}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
