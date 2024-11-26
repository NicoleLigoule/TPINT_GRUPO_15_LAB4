package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Provincia;
import entidades.TipoDeCuenta;
import negocio.DDL;
import negocio.NegocioClientes;
import negocio.NegocioCuentas;

/**
 * Servlet implementation class servletReporteClientes
 */
@WebServlet("/servletReporteClientes")
public class servletReporteClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReporteClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LLEGA AL SERVLET - get");
		if (request.getParameter("Param") != null) {
			DDL listado = new DDL(); 
			try 
			{
				System.out.println("ENTRA AL TRY-CATCH");
				ArrayList<Provincia> listaProvincia = listado.Provincia();
                if (listaProvincia != null && !listaProvincia.isEmpty()) {
                    request.setAttribute("listaProvincia", listaProvincia);
                } else {
                    response.getWriter().println("No se encontraron provincias.");
                }
			}
			catch (Exception e) 
			{
				e.printStackTrace();
                response.getWriter().println("Error al obtener los datos: " + e.getMessage());
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("ReporteClientes.jsp");
            rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LLEGA AL SERVLET Cliente- post");
		
		int idProvincia = 0;
		
		 String provinciaParam = request.getParameter("provincia");
		    System.out.println("Valor recibido para provincia: " + provinciaParam);
		    
		    if (provinciaParam != null && !provinciaParam.isEmpty()) {
		        try {
		            idProvincia = Integer.parseInt(provinciaParam);
		            System.out.println("Id provincia seleccionado: " + idProvincia);
		        } catch (NumberFormatException e) {
		            System.out.println("Error: El valor recibido no es un número válido: " + provinciaParam);
		        }
		    } else {
		        System.out.println("No se recibió el parámetro provincia o está vacío.");
		    }
		
		    NegocioClientes negocioClientes = new NegocioClientes();
		    try
		    {
		    	System.out.print("entra a lista, idProv= "+idProvincia);
		    	List<Cliente> clientesPorProvincia = negocioClientes.obtenerClientesPorIdProvincia(idProvincia);
		    	System.out.println("Resultado obtenido: " + clientesPorProvincia);
		    	if (clientesPorProvincia != null && !clientesPorProvincia.isEmpty()) 
		    	{
		    		System.out.println("Contenido de listaCuenta:");
                    for (Object cli : clientesPorProvincia) 
                    {
                        System.out.println(cli);
                    }
                    request.setAttribute("clientesPorProvincia", clientesPorProvincia); 
                    
                    int cantidadResultados = clientesPorProvincia.size();
                    request.setAttribute("cantidadResultados", cantidadResultados);
                    
		    	} else {
                    request.setAttribute("Mensaje", "No se encontraron clientes.");                
		    	}
		    	
		    	RequestDispatcher rd = request.getRequestDispatcher("ReporteClientes.jsp");
		         rd.forward(request, response);
			} 
		    catch (Exception e) 
		    {
		    	e.printStackTrace();response.getWriter().println("Error al obtener la lista de clientes: " + e.getMessage());
			}
	             
     		
	}

}
