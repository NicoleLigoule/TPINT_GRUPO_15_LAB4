package entidades;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimiento {

	    private int idMovimientoMov;           
	    private LocalDateTime fechaMovimientoMov; 
	    private String detalleMov;            
	    private BigDecimal importeMov;        
	    private int idTipoMovTMMov;           

	    // Constructores
	    public Movimiento() {
	         
	    }

	    
	    public Movimiento(int idMovimientoMov, LocalDateTime fechaMovimientoMov, String detalleMov, BigDecimal importeMov, int idTipoMovTMMov) {
	        this.idMovimientoMov = idMovimientoMov;
	        this.fechaMovimientoMov = fechaMovimientoMov;
	        this.detalleMov = detalleMov;
	        this.importeMov = importeMov;
	        this.idTipoMovTMMov = idTipoMovTMMov;
	    }

	    // Getters y setters
	    public int getIdMovimientoMov() {
	        return idMovimientoMov;
	    }

	    public void setIdMovimientoMov(int idMovimientoMov) {
	        this.idMovimientoMov = idMovimientoMov;
	    }

	    public LocalDateTime getFechaMovimientoMov() {
	        return fechaMovimientoMov;
	    }

	    public void setFechaMovimientoMov(LocalDateTime fechaMovimientoMov) {
	        this.fechaMovimientoMov = fechaMovimientoMov;
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


	    // Override toString()
		@Override
		public String toString() {
			return "Movimiento [idMovimientoMov=" + idMovimientoMov + ", fechaMovimientoMov=" + fechaMovimientoMov
					+ ", detalleMov=" + detalleMov + ", importeMov=" + importeMov + ", idTipoMovTMMov=" + idTipoMovTMMov
					+ "]";
		}
	    
	}

