package entidades;

public class Nacionalidad {
	
	    private String idNacionalidadNc; 
	    private String descripcionNc; 

	    // Constructor
	    public Nacionalidad() {
	    	
	    }
	    
	    public Nacionalidad(String idNacionalidadNc, String descripcionNc) {
	        this.idNacionalidadNc = idNacionalidadNc;
	        this.descripcionNc = descripcionNc;
	    }

	    // Getters y Setters
	    public String getIdNacionalidadNc() {
	        return idNacionalidadNc;
	    }

	    public void setIdNacionalidadNc(String idNacionalidadNc) {
	        this.idNacionalidadNc = idNacionalidadNc;
	    }

	    public String getDescripcionNc() {
	        return descripcionNc;
	    }

	    public void setDescripcionNc(String descripcionNc) {
	        this.descripcionNc = descripcionNc;
	    }

	    // Método toString() 
		@Override
		public String toString() {
			return "Nacionalidad [idNacionalidadNc=" + idNacionalidadNc + ", descripcionNc=" + descripcionNc + "]";
		}

	    
	

}
