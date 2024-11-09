package entidades;
import java.time.LocalDate;

public class CuotasXPrestamo {

	    private int idPrestamoPtCp; 
	    private LocalDate fechaVencimientoCp; 
	    private int nCuota; 

	    // Constructores
	    public CuotasXPrestamo() {
	    	
	    }
	    
	    public CuotasXPrestamo(int idPrestamoPtCp, LocalDate fechaVencimientoCp, int nCuota) {
	        this.idPrestamoPtCp = idPrestamoPtCp;
	        this.fechaVencimientoCp = fechaVencimientoCp;
	        this.nCuota = nCuota;
	    }

	    // Getters y setters
	    public int getIdPrestamoPtCp() {
	        return idPrestamoPtCp;
	    }

	    public void setIdPrestamoPtCp(int idPrestamoPtCp) {
	        this.idPrestamoPtCp = idPrestamoPtCp;
	    }

	    public LocalDate getFechaVencimientoCp() {
	        return fechaVencimientoCp;
	    }

	    public void setFechaVencimientoCp(LocalDate fechaVencimientoCp) {
	        this.fechaVencimientoCp = fechaVencimientoCp;
	    }

	    public int getNCuota() {
	        return nCuota;
	    }

	    public void setNCuota(int nCuota) {
	        this.nCuota = nCuota;
	    }
	}

