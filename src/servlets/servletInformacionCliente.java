package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.ClienteCuentaDTO;
import entidades.Usuario;
import negocio.DDL;

/**
 * Servlet implementation class servletInformacionCliente
 */
@WebServlet("/servletInformacionCliente")
public class servletInformacionCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletInformacionCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.print("LLEGA AL SERVLET ");
		HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Obtener el CUIL del usuario
        String cuil = usuario.getCuilUs();
        System.out.print("Cuil usuario: "+ cuil);
        
        if(cuil != null)
        {
        	DDL ddl = new DDL();        	
        	
        	try{
        		
        		ArrayList<ClienteCuentaDTO> infoCliente = ddl.infoClienteCtas_2(cuil);
        		System.out.println("lista de infoClientes: "+infoCliente);
        		 
        		if (infoCliente != null && !infoCliente.isEmpty())
        		{
        			 ClienteCuentaDTO cliente = infoCliente.get(0);
        			 String nombreApellido = cliente.getNombre() + " " + cliente.getApellido();
        			 String correo = cliente.getCorreoElectronico();
                     String telefono = cliente.getTelefono();
                     String direccion = cliente.getDireccion();
                     
                     request.setAttribute("usuario", usuario);
                     request.setAttribute("nombreApellido", nombreApellido);
                     request.setAttribute("correo", correo);
                     request.setAttribute("telefono", telefono);
                     request.setAttribute("direccion", direccion);
                     request.setAttribute("listaCuentas", infoCliente);
        		}else {
                    // Si no hay cuentas ni nombre, muestra un mensaje
                    request.setAttribute("mensaje", "No se encontraron clientes con CUIL: " + cuil);
                }
            } catch (Exception e) {
                e.printStackTrace();
                
                request.setAttribute("mensaje", "Error al obtener las cuentas: " + e.getMessage());
            }
               		
              
        }
       
        
        

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("InicioCliente.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
