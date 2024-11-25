package entidades;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Prestamo {

    private int idPrestamoPt;              
    private int numeroDeCuentaCuPt;        
    private LocalDate fechaPeticionPt; 
    private BigDecimal importeSolicitadoPt;
    private String plazoPagoPt;            
    private String detalleSolicitudPt;
    private double montoCuota;
    private boolean estadoPt;           
    private int cuotasPagadas;
    
    private Cuenta cuenta; // Relación con la tabla Cuenta              
    private InteresesXCantidadDeMeses interes; // Relacionado con la tabla InteresesXCantidadDeMeses

    // Constructores
    public Prestamo() {
    }

    // Constructor con parámetros
    public Prestamo(int numeroDeCuentaCuPt, LocalDate fechaPeticionPt, BigDecimal importeSolicitadoPt,
                    String plazoPagoPt, String detalleSolicitudPt, boolean estadoPt) {
        this.numeroDeCuentaCuPt = numeroDeCuentaCuPt;
        this.fechaPeticionPt = fechaPeticionPt;
        this.importeSolicitadoPt = importeSolicitadoPt;
        this.plazoPagoPt = plazoPagoPt;
        this.detalleSolicitudPt = detalleSolicitudPt;
        this.estadoPt = estadoPt;
    }

    // Getters y setters
    public int getIdPrestamoPt() {
        return idPrestamoPt;
    }

    public void setIdPrestamoPt(int idPrestamoPt) {
        this.idPrestamoPt = idPrestamoPt;
    }

    public int getNumeroDeCuentaCuPt() {
        return numeroDeCuentaCuPt;
    }

    public void setNumeroDeCuentaCuPt(int numeroDeCuentaCuPt) {
        this.numeroDeCuentaCuPt = numeroDeCuentaCuPt;
    }

    public LocalDate getFechaPeticionPt() {
        return fechaPeticionPt;
    }

    public void setFechaPeticionPt(LocalDate localDate) {
        this.fechaPeticionPt = localDate;
    }

    public BigDecimal getImporteSolicitadoPt() {
        return importeSolicitadoPt;
    }

    public void setImporteSolicitadoPt(BigDecimal importeSolicitadoPt) {
        this.importeSolicitadoPt = importeSolicitadoPt;
    }

    public String getPlazoPagoPt() {
        return plazoPagoPt;
    }

    public void setPlazoPagoPt(String plazoPagoPt) {
        this.plazoPagoPt = plazoPagoPt;
    }

    public String getDetalleSolicitudPt() {
        return detalleSolicitudPt;
    }

    public void setDetalleSolicitudPt(String detalleSolicitudPt) {
        this.detalleSolicitudPt = detalleSolicitudPt;
    }

    public boolean isEstadoPt() {
        return estadoPt;
    }

    public void setEstadoPt(boolean estadoPt) {
        this.estadoPt = estadoPt;
    }

    public double getMontoRestante() {
        return importeSolicitadoPt.doubleValue() - (cuotasPagadas * montoCuota);
    }
    
    // Relaciones con otras entidades
    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public InteresesXCantidadDeMeses getInteres() {
        return interes;
    }

    public void setInteres(InteresesXCantidadDeMeses interes) {
        this.interes = interes;
    }

    // Método toString() 
    @Override
    public String toString() {
        return "Prestamo [idPrestamoPt=" + idPrestamoPt + ", numeroDeCuentaCuPt=" + numeroDeCuentaCuPt
                + ", fechaPeticionPt=" + fechaPeticionPt + ", importeSolicitadoPt=" + importeSolicitadoPt
                + ", plazoPagoPt=" + plazoPagoPt + ", detalleSolicitudPt=" + detalleSolicitudPt + ", estadoPt="
                + estadoPt + ", cuenta=" + cuenta + ", interes=" + interes + "]";
    }
}
