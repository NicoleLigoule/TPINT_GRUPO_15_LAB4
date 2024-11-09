package entidades;

public class TipoMovimiento {	

	    private int idTipoMovTM;        
	    private String descripcionTM;   

	    // Constructores
	    public TipoMovimiento() {
	    }

	    
	    public TipoMovimiento(int idTipoMovTM, String descripcionTM) {
	        this.idTipoMovTM = idTipoMovTM;
	        this.descripcionTM = descripcionTM;
	    }

	    // Getters y setters
	    public int getIdTipoMovTM() {
	        return idTipoMovTM;
	    }

	    public void setIdTipoMovTM(int idTipoMovTM) {
	        this.idTipoMovTM = idTipoMovTM;
	    }

	    public String getDescripcionTM() {
	        return descripcionTM;
	    }

	    public void setDescripcionTM(String descripcionTM) {
	        this.descripcionTM = descripcionTM;
	    }


	    // Override toString() 
		@Override
		public String toString() {
			return "TipoMovimiento [idTipoMovTM=" + idTipoMovTM + ", descripcionTM=" + descripcionTM + "]";
		}

	    
	}

