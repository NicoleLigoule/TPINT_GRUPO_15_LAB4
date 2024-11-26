package entidades;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimiento {

    private int idMovimientoMov;           
    private String fechaMovimientoMov; 
    private String detalleMov;            
    private BigDecimal importeMov;        
    private int idTipoMovTMMov;           
    private String descripcionTipoMov;   // Nueva propiedad para la descripción del tipo de movimiento

    // Constructores
    public Movimiento() {
    }

    public Movimiento(int idMovimientoMov, String fechaMovimientoMov, String detalleMov, 
                      BigDecimal importeMov, int idTipoMovTMMov, String descripcionTipoMov) {
        this.idMovimientoMov = idMovimientoMov;
        this.fechaMovimientoMov = fechaMovimientoMov;
        this.detalleMov = detalleMov;
        this.importeMov = importeMov;
        this.idTipoMovTMMov = idTipoMovTMMov;
        this.descripcionTipoMov = descripcionTipoMov; // Inicializar la descripción
    }

    // Getters y setters
    public int getIdMovimientoMov() {
        return idMovimientoMov;
    }

    public void setIdMovimientoMov(int idMovimientoMov) {
        this.idMovimientoMov = idMovimientoMov;
    }

    public String getFechaMovimientoMov() {
        return fechaMovimientoMov;
    }

    public void setFechaMovimientoMov(String fechaMovimiento) {
        this.fechaMovimientoMov = fechaMovimiento;
    }

    public String getDetalleMov() {
        return detalleMov;
    }

    public void setDetalleMov(String detalleMov) {
        this.detalleMov = detalleMov;
    }

    public BigDecimal getImporteMov() {
        return importeMov;
    }

    public void setImporteMov(BigDecimal importeMov) {
        this.importeMov = importeMov;
    }

    public int getIdTipoMovTMMov() {
        return idTipoMovTMMov;
    }

    public void setIdTipoMovTMMov(int idTipoMovTMMov) {
        this.idTipoMovTMMov = idTipoMovTMMov;
    }

    // Getter y Setter para la nueva propiedad descripcionTipoMov
    public String getDescripcionTipoMov() {
        return descripcionTipoMov;
    }

    public void setDescripcionTipoMov(String descripcionTipoMov) {
        this.descripcionTipoMov = descripcionTipoMov;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Movimiento [idMovimientoMov=" + idMovimientoMov + ", fechaMovimientoMov=" + fechaMovimientoMov
                + ", detalleMov=" + detalleMov + ", importeMov=" + importeMov + ", idTipoMovTMMov=" + idTipoMovTMMov
                + ", descripcionTipoMov=" + descripcionTipoMov + "]"; // Mostrar también la descripción en el toString
    }
}
