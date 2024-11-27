package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioPrestamo;
import entidades.InteresesXCantidadDeMeses;

@WebServlet("/ServletConfirmarPrestamo")
public class ServletConfirmarPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletConfirmarPrestamo() {
        super();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String cuentaDestino = request.getParameter("cuenta_destino");
            double importeSolicitado = Double.parseDouble(request.getParameter("importe_solicitado"));
            String plazoPago = request.getParameter("plazo_pago");
            String Motivo = request.getParameter("motivo");
            

            NegocioPrestamo negocioPrestamo = new NegocioPrestamo();

            // Obtener el interes utilizando la capa de negocio
            InteresesXCantidadDeMeses interes = negocioPrestamo.InteresesdelPrestamo(plazoPago);
            
            if (interes == null) {
                request.setAttribute("mensajeError", "El plazo de pago no es valido.");
                request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
                return;
            }

            // Realizar calculos
            int plazoPagoMeses = interes.getMeses();
            double porcentajeInteresDecimal = (double) interes.getInteresIxm().floatValue() / 100;
            double porcentajeDeAnioSegunMeses = (double) 12 / interes.getMeses();

            double montoConInteres = importeSolicitado + (importeSolicitado * porcentajeInteresDecimal * porcentajeDeAnioSegunMeses);
            double montoPorCuota = montoConInteres / plazoPagoMeses;

            // Establecer los atributos para el JSP
            request.setAttribute("cuenta_destino", cuentaDestino);
            request.setAttribute("importe_solicitado", importeSolicitado);
            request.setAttribute("monto_con_interes", montoConInteres);
            request.setAttribute("plazo_pago", interes.getMeses());
            request.setAttribute("plazo_pago_IXM", interes.getPlazoDPagosEnMesesIxm());
            request.setAttribute("monto_por_cuota", montoPorCuota);
            request.setAttribute("motivo", Motivo);

            request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("mensajeError", "Hubo un error al procesar los datos. Verifique los valores ingresados.");
            request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Hubo un problema al procesar la solicitud de prestamo.");
            request.getRequestDispatcher("ConfirmarPrestamo.jsp").forward(request, response);
        }
    }
}
