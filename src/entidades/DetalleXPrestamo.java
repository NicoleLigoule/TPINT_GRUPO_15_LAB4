package entidades;
import java.math.BigDecimal;

public class DetalleXPrestamo {

	    private int idPrestamoPtDt; 
	    private BigDecimal importeCInteresDt; 
	    private BigDecimal importeXCuotasDt; 
	    private int cantidadCuotasDt; 

	    // Constructores
	    public DetalleXPrestamo() {
	    }
	    
	    
	    public DetalleXPrestamo(BigDecimal importeCInteresDt, BigDecimal importeXCuotasDt, int cantidadCuotasDt) {
	        this.importeCInteresDt = importeCInteresDt;
	        this.importeXCuotasDt = importeXCuotasDt;
	        this.cantidadCuotasDt = cantidadCuotasDt;
	    }
	    

	    // Getters y setters
	    public int getIdPrestamoPtDt() {
	        return idPrestamoPtDt;
	    }
	    

	    public BigDecimal getImporteCInteresDt() {
	        return importeCInteresDt;
	    }

	    public void setImporteCInteresDt(BigDecimal importeCInteresDt) {
	        this.importeCInteresDt = importeCInteresDt;
	    }

	    public BigDecimal getImporteXCuotasDt() {
	        return importeXCuotasDt;
	    }

	    public void setImporteXCuotasDt(BigDecimal importeXCuotasDt) {
	        this.importeXCuotasDt = importeXCuotasDt;
	    }

	    public int getCantidadCuotasDt() {
	        return cantidadCuotasDt;
	    }

	    public void setCantidadCuotasDt(int cantidadCuotasDt) {
	        this.cantidadCuotasDt = cantidadCuotasDt;
	    }
	}

