package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.ClienteCuentaDTO;
import negocio.DDL;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LLEGA AL SERVLET DE CUENTA");
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
                    // Si no hay cuentas, muestra un mensaje
                    request.setAttribute("mensaje", "No se encontraron cuentas para el cliente con CUIL: " + cuil);
                }
            } catch (Exception e) {
                e.printStackTrace();
                
                request.setAttribute("mensaje", "Error al obtener las cuentas: " + e.getMessage());
            }
        } else {
            // Si el CUIL es inválido
            request.setAttribute("mensaje", "CUIL invalido proporcionado.");
        }

       
        RequestDispatcher rd = request.getRequestDispatcher("EliminarCuenta.jsp");
        rd.forward(request, response);
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
