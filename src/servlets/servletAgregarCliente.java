package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abml.abmlCliente;
import entidades.Cliente;
import entidades.Nacionalidad;
import entidades.Sexo;
import entidades.Provincia;
import entidades.Localidad;

import negocio.DDL;

/**
 * Servlet implementation class servletAgregarCliente
 */
@WebServlet("/servletAgregarCliente")
public class servletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletAgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.print("ENTRO AL DOGET");
        if (request.getParameter("Param") != null | request.getParameter("provincia") != null) {
            DDL lista = new DDL();
            try {

                ArrayList<Nacionalidad> listaNacionalidades = lista.Nacionalidad();
                if (listaNacionalidades != null && !listaNacionalidades.isEmpty()) {
                    request.setAttribute("listaNacionalidad", listaNacionalidades);
                } else {
                    response.getWriter().println("No se encontraron nacionalidades.");
                }

                ArrayList<Sexo> listaSexos = lista.Sexo(); 
                if (listaSexos != null && !listaSexos.isEmpty()) {
                    request.setAttribute("listaSexo", listaSexos);
                } else {
                    response.getWriter().println("No se encontraron sexos.");
                }

                
                ArrayList<Provincia> listaProvincias = lista.Provincia();
                if (listaProvincias != null && !listaProvincias.isEmpty()) {
                    request.setAttribute("listaProvincias", listaProvincias);
                } else {
                    response.getWriter().println("No se encontraron provincias.");
                }
                
                if(request.getParameter("provincia") != null) {
                	int idProvincia = Integer.parseInt(request.getParameter("provincia"));
                    ArrayList<Localidad> listaLocalidad = lista.Localidad(idProvincia);
                    if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
                        request.setAttribute("listaLocalidad", listaLocalidad);
                    } else {
                        response.getWriter().println("No se encontraron Localidad.");
                    }
                }               
                
                
                RequestDispatcher rd = request.getRequestDispatcher("AgregarCliente.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener los datos: " + e.getMessage());
            }
        }
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("submit-button")!=null) {
			Cliente c = new Cliente();
			c.setDni(Integer.parseInt(request.getParameter("dni")));
			c.setCuil(request.getParameter("cuil"));
			c.setNombre(request.getParameter("nombre"));
			c.setApellido(request.getParameter("apellido"));
			c.setId_sexo(Integer.parseInt(request.getParameter("genero")));
			c.setId_nacionalidad(request.getParameter("nacionalidad"));
            String fechaStr = request.getParameter("fecha-nacimiento");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);
            c.setFechaNacimiento(fechaNacimiento);
            c.setId_localidad(Integer.parseInt(request.getParameter("localidad")));
            c.setId_provincia(Integer.parseInt(request.getParameter("provincia")));
			c.setId_nacionalidad(request.getParameter("nacionalidad"));
            c.setDireccion(request.getParameter("direccion"));
            c.setTelefono(request.getParameter("telefono"));
            c.setCorreo(request.getParameter("email"));

            abmlCliente abmlcliente = new abmlCliente();

            boolean insert = abmlcliente.agregarCliente(c);
            if(insert) {
            	request.setAttribute("Correcto", "Se ha generado el Cliente correctamente.");
            }else {
            	request.setAttribute("Error", "Error al agregar Cliente.");
            }
            request.getRequestDispatcher("/AgregarCliente.jsp").forward(request, response);

            System.out.println("Datos recibidos en servletCliente: DNI = " + request.getParameter("dni"));
            System.out.println("CUIL: " + request.getParameter("cuil"));
            System.out.println("Nombre: " + request.getParameter("nombre"));
            System.out.println("Apellido: " + request.getParameter("apellido"));
            System.out.println("Localidad: " + request.getParameter("localidad"));
            System.out.println("Provincia: " + request.getParameter("provincia"));
            System.out.println("Direccion: " + request.getParameter("direccion"));
            System.out.println("Telefono: " + request.getParameter("telefono"));
            System.out.println("email: " + request.getParameter("email"));
		}
	}
}
