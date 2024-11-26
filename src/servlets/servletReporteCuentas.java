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

import entidades.Cuenta;
import entidades.Sexo;
import entidades.TipoDeCuenta;
import negocio.DDL;
import negocio.NegocioCuentas;

/**
 * Servlet implementation class servletReporteCuentas
 */
@WebServlet("/servletReporteCuentas")
public class servletReporteCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletReporteCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("LLEGA AL SERVLET - get");
		
		if (request.getParameter("Param") != null) {
			DDL tipo = new DDL(); 
			try 
			{
				System.out.println("ENTRA AL TRY-CATCH");
				ArrayList<TipoDeCuenta> listaTipos = tipo.TipoDecuenta();
                if (listaTipos != null && !listaTipos.isEmpty()) {
                    request.setAttribute("listaTipos", listaTipos);
                } else {
                    response.getWriter().println("No se encontraron tipos.");
                }
			}
			catch (Exception e) 
			{
				e.printStackTrace();
                response.getWriter().println("Error al obtener los datos: " + e.getMessage());
			}

                
                RequestDispatcher rd = request.getRequestDispatcher("ReporteCuentas.jsp");
                rd.forward(request, response);

        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Declaro las variables para que sean accesibles
		int tipoCuenta = 0;
        LocalDate desde = null;
        LocalDate hasta = null;
		
		System.out.println("LLEGA AL SERVLET - post");
		//CARGAMOS EL ID TIPO
		 if (request.getParameter("mostrarBtn") != null) {
		        String tipo = request.getParameter("tipo");
		        if (tipo != null && !tipo.isEmpty()) {
		        	tipoCuenta = Integer.parseInt(tipo);
		            System.out.println("Tipo de cuenta seleccionado: " + tipoCuenta);
		        } else {
		            System.out.println("No se recibió el tipo de cuenta.");
		        }
		    
		        
		        
		        
		      //CARGAMOS FECHA DESDE
            String fechaDesde = request.getParameter("fecha-desde");
            if (fechaDesde != null && !fechaDesde.isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    desde = LocalDate.parse(fechaDesde, formatter);
                    System.out.println("Fecha desde: " + desde);
                } catch (DateTimeParseException e) {
                    System.err.println("Error al parsear la fecha-desde: " + fechaDesde);
                    e.printStackTrace();
                }
            } else {
                System.out.println("fecha-desde no proporcionada o vacía");
            }
            //CARGAMOS FECHA HASTA
            String fechaHasta = request.getParameter("fecha-hasta");
            if (fechaHasta != null && !fechaHasta.isEmpty()) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    hasta = LocalDate.parse(fechaHasta, formatter);
                    System.out.println("Fecha hasta: " + hasta);
                } catch (DateTimeParseException e) {
                    System.err.println("Error al parsear la fecha-hasta: " + fechaHasta);
                    e.printStackTrace();
                }
            } else {
                System.out.println("fecha-hasta no proporcionada o vacía");
            }
            
            //----
            NegocioCuentas negCue = new NegocioCuentas();
            try 
            {
            	System.out.print("entra a lista");
            	List<Cuenta> listaCuenta =  negCue.obtenerCuentasReporte(tipoCuenta, desde, hasta);
            	
            	if (listaCuenta != null && !listaCuenta.isEmpty()) {
                    System.out.println("Contenido de listaCuenta:");
                    for (Object cuenta : listaCuenta) {
                        System.out.println(cuenta);
                    }
                    request.setAttribute("listaCuenta", listaCuenta); 
                    
                    int cantidadResultados = listaCuenta.size();
                    request.setAttribute("cantidadResultados", cantidadResultados);

                } else {
                    request.setAttribute("Mensaje", "No se encontraron cuentas.");
                }

                
                RequestDispatcher rd = request.getRequestDispatcher("ReporteCuentas.jsp");
                rd.forward(request, response);
            	
			} 
            catch (Exception e) 
            {
            	e.printStackTrace();response.getWriter().println("Error al obtener la lista de cuentas: " + e.getMessage());
            	
			}
		}
	}

}
