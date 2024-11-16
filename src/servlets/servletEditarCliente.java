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
import entidades.Localidad;
import entidades.Cliente;
import entidades.Nacionalidad;
import entidades.Provincia;
import entidades.Sexo;
import negocio.DDL;
import negocio.NegocioClientes;

/**
 * Servlet implementation class servletEditarCliente
 */
@WebServlet("/servletEditarCliente")
public class servletEditarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletEditarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("Param") != null ) {
            DDL lista = new DDL();
            try {
   
                ArrayList<Cliente> listaClientes = lista.ClienteList();
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    request.setAttribute("listaCliente", listaClientes);
                } else {
                    response.getWriter().println("No se encontraron Clientes.");
                }
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
                
                
                RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
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
		 
		if (request.getParameter("Cliente") != null) {
			String cuilBuscado = request.getParameter("Cliente");
			System.out.println(cuilBuscado);
			
		    DDL lista = new DDL();
		    
            try {
            	request.setAttribute("clienteSeleccionado", cuilBuscado);  
                ArrayList<Cliente> listaClientes = lista.ClienteList();
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    request.setAttribute("listaCliente", listaClientes);
                } else {
                    response.getWriter().println("No se encontraron Clientes.");
                }
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
                ///aca buscar en que provincia esta la localidad del cliente 
                Cliente clienteSe = new Cliente();
                
				for (Cliente cliente : listaClientes) {

					if (cliente.getCuil().equals(cuilBuscado)) {
						System.out.println("Lo encontre en el servlets");

						clienteSe = cliente;
						System.out.println(clienteSe.getId_localidad());
						break;
					}
				}
				int h=0;
				NegocioClientes neg= new NegocioClientes();
				 h=neg.obtenProv(String.valueOf(clienteSe.getId_localidad()));
                
                if(h != 0) {
                	int idProvincia =h;
                	   // Guardar 'h' como atributo del request
                    request.setAttribute("idProvincia", idProvincia);
                    
                	
                    ArrayList<Localidad> listaLocalidad = lista.Localidad(idProvincia);
                    if (listaLocalidad != null && !listaLocalidad.isEmpty()) {
                        request.setAttribute("listaLocalidad", listaLocalidad);
                    } else {
                        response.getWriter().println("No se encontraron Localidad.");
                    }
                }               
                
                
                RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al obtener los datos: " + e.getMessage());
            }
		}
		 if(request.getParameter("agregarBtn") != null) {
	            Cliente c = new Cliente();
	            
	            String dni = request.getParameter("dni");
	            if (dni != null && !dni.isEmpty()) {
	                c.setDni(Integer.parseInt(dni));
	            }
	            System.out.println("Datos recibidos en servletCliente: DNI = " + dni);
	            
	            c.setCuil(request.getParameter("cuil"));
	            System.out.println("CUIL: " + request.getParameter("cuil"));
	            
	            c.setNombre(request.getParameter("nombre"));
	            System.out.println("Nombre: " + request.getParameter("nombre"));
	            
	            c.setApellido(request.getParameter("apellido"));
	            System.out.println("Apellido: " + request.getParameter("apellido")); 
	            

	            String genero = request.getParameter("genero");
	            if (genero != null && !genero.isEmpty()) {
	                c.setId_sexo(Integer.parseInt(genero));
	                System.out.println("Genero: " + genero);
	            }

	            String fechaStr = request.getParameter("fecha-nacimiento");
	            if (fechaStr != null && !fechaStr.isEmpty()) {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	                LocalDate fechaNacimiento = LocalDate.parse(fechaStr, formatter);
	                c.setFechaNacimiento(fechaNacimiento);
	                System.out.println("FechaNacimiento: " + request.getParameter("fecha-nacimiento"));  
	            }

	            String nacionalidad = request.getParameter("nacionalidad");
	            if (nacionalidad != null && !nacionalidad.isEmpty()) {
	                try {
	                    c.setId_nacionalidad(nacionalidad);
	                    System.out.println("Nacionalidad: " + request.getParameter("nacionalidad"));
	                  
	                } catch (NumberFormatException e) {
	                    System.out.println("Error: el valor de nacionalidad no es un número válido.");
	                    request.setAttribute("Error", "El valor de nacionalidad no es válido.");
	                    request.getRequestDispatcher("/AgregarCliente.jsp").forward(request, response);
	                    return;
	                }
	            }

	            String localidad = request.getParameter("localidad");
	            if (localidad != null && !localidad.isEmpty()) {
	                c.setId_localidad(Integer.parseInt(localidad));
	                System.out.println("Localidad: " + localidad);
	            }

	            String provincia = request.getParameter("provincia");
	            if (provincia != null && !provincia.isEmpty()) {
	                c.setId_provincia(Integer.parseInt(provincia));
	                System.out.println("Provincia: " + request.getParameter("provincia"));
	            }

	            c.setDireccion(request.getParameter("direccion"));
	            System.out.println("Direccion: " + request.getParameter("direccion"));
	            
	            c.setTelefono(request.getParameter("telefono"));
	            System.out.println("Telefono: " + request.getParameter("telefono"));
	            
	            c.setCorreo(request.getParameter("email"));
	            System.out.println("Email: " + request.getParameter("email"));

	            abmlCliente abmlcliente = new abmlCliente();
	            boolean insert = abmlcliente.editarCliente(c);

		            if (insert) {         
		                response.sendRedirect("EditarCliente.jsp?status=success");
		            } else {
		                request.setAttribute("Error", "Error al agregar Cliente.");
		                RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
		                rd.forward(request, response);
		            }   
	        	}
		
	}
}
