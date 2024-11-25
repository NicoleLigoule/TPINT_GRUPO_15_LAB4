package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.Usuario;
import negocio.NegocioCuentas;
import negocio.NegocioTransferencia;

@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NegocioTransferencia transferenciaNegocio;

    public servletTransferencia() {
        super();
        this.transferenciaNegocio = new NegocioTransferencia(); // Instancia de negocio
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ENTRO AL DOGET TRANSFERENCIA");

        // Obtener el usuario desde la sesi�n
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        // Si no hay usuario logueado, redirigir al login
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Obtener el CUIL del usuario logueado
        String cuil = usuario.getCuilUs(); // Obtener CUIL del usuario logueado
        System.out.println("CUIL del usuario: " + cuil); // Agregar para depuraci�n
        
        // Obtener las cuentas asociadas al CUIL
        NegocioCuentas negocioCuentas = new NegocioCuentas();
        List<Cuenta> cuentas = negocioCuentas.obtenerCuentasPorCuil(cuil);
        
        // Verificar si se encontraron cuentas
        if (cuentas == null || cuentas.isEmpty()) {
            System.out.println("No se encontraron cuentas para el CUIL: " + cuil);
        } else {
            // Imprimir las cuentas encontradas para depuraci�n
            for (Cuenta cuenta : cuentas) {
                System.out.println("Cuenta encontrada: ID = " + cuenta.getNumeroDeCuentaCu() + ", Descripci�n = " + cuenta.getSaldoCu());
            }
        }

        // Establecer las cuentas como atributo para el JSP
        request.setAttribute("cuentas", cuentas);

        // Redirigir a la p�gina Transferencia.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("Transferencia.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // Verificar si el usuario est� logueado
        Usuario usuario = (Usuario) httpRequest.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Si se hace clic en el bot�n de realizar transferencia
        if (request.getParameter("RealizarBtn") != null) {
            try {
                // Obtener los valores de la transferencia desde los par�metros
                int cuentaOrigen = Integer.parseInt(request.getParameter("cuentaOrigen"));
                String cbuCuentaDestino = request.getParameter("cbu");  // Aseg�rate de que este par�metro se llama "cbu"
                double monto = Double.parseDouble(request.getParameter("monto"));
                String detalle = request.getParameter("detalle"); // Este es el detalle de la transferencia

                // Obtener el CUIL desde la sesi�n
                String cuil = usuario.getCuilUs();

                // Llamar al negocio para realizar la transferencia
                transferenciaNegocio.realizarTransferencia(cuentaOrigen, cbuCuentaDestino, monto, detalle);

                // Redirigir a la p�gina de �xito
                response.sendRedirect("transferenciaExitosa.jsp");

            } catch (Exception e) {
                // Manejar error y enviar mensaje al JSP
                e.printStackTrace();
                request.setAttribute("error", "Error en la transferencia: " + e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
                rd.forward(request, response);
            }
        } else {
            // Si no se hizo clic en "Realizar", redirigir de nuevo al JSP
            RequestDispatcher rd = request.getRequestDispatcher("Transferencia.jsp");
            rd.forward(request, response);
        }
    }
}
