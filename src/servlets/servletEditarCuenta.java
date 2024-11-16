package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abml.abmlCuenta;
import entidades.Cuenta;
import entidades.TipoDeCuenta;

@WebServlet("/servletEditarCuenta")
public class servletEditarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private abmlCuenta cuentaManager;

    @Override
    public void init() throws ServletException {
        cuentaManager = new abmlCuenta();
        System.out.println("Servlet inicializado y cuentaManager creado");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cuil = request.getParameter("cuil");
        System.out.println("doGet - CUIL recibido: " + cuil);

        if (cuil != null && !cuil.isEmpty()) {
            List<Cuenta> listaCuentas = cuentaManager.obtenerCuentasPorCuil(cuil);
            System.out.println("doGet - Cuentas obtenidas: " + (listaCuentas != null ? listaCuentas.size() : 0));

            if (listaCuentas != null && !listaCuentas.isEmpty()) {
                request.setAttribute("cuentas", listaCuentas);
                System.out.println("doGet - Cuentas asignadas al request");
            } else {
                request.setAttribute("mensaje", "No se encontraron cuentas para el cliente con Cuil: " + cuil);
                System.out.println("doGet - No se encontraron cuentas para el Cuil: " + cuil);
            }
            
            List<TipoDeCuenta> listaTipos = cuentaManager.obtenerTiposTodos();
            
            if(listaTipos != null && !listaTipos.isEmpty()) {
            	request.setAttribute("tipos", listaTipos);
            	System.out.println("doGet - Tipos de Cuentas asignadas al request");
            }else {
            	System.out.println("doGet - No se encontraron tipos de cuentas en la db");
            }
            
            
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarCuenta.jsp");
        dispatcher.forward(request, response);
        System.out.println("doGet - Redirigiendo a EditarCuenta.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int numeroDeCuenta = Integer.parseInt(request.getParameter("NroDeCuenta"));
            int tipoDeCuenta = Integer.parseInt(request.getParameter("tipoDeCuenta"));
            System.out.println("doPost - Número de cuenta: " + numeroDeCuenta + ", Tipo de cuenta: " + tipoDeCuenta);

            Cuenta cuenta = cuentaManager.obtenerCuenta(numeroDeCuenta);
            if (cuenta != null) {
                cuenta.setIdTipoCuenta(tipoDeCuenta);
                System.out.println("doPost - Cuenta encontrada y tipo de cuenta actualizado");

                boolean exito = cuentaManager.editarCuenta(cuenta);
                System.out.println("doPost - Resultado de la edición: " + (exito ? "Éxito" : "Fallo"));
                
                request.setAttribute("mensaje", exito ? "Cuenta actualizada exitosamente" : "Error al actualizar la cuenta");
            } else {
                request.setAttribute("mensaje", "Cuenta no encontrada");
                System.out.println("doPost - Cuenta no encontrada con el número: " + numeroDeCuenta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Error al procesar la solicitud: " + e.getMessage());
            System.out.println("doPost - Excepción al procesar la solicitud: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("EditarCuenta.jsp");
        dispatcher.forward(request, response);
        System.out.println("doPost - Redirigiendo a EditarCuenta.jsp");
    }
}
