package entidades;

public class TipoDeCuenta {
	
	    private int idTipoCuenta; 
	    private String nombreTipo; 

	    // Constructores
	    public TipoDeCuenta() {
	    }

	    
	    public TipoDeCuenta(int idTipoCuenta, String nombreTipo) {
	        this.idTipoCuenta = idTipoCuenta;
	        this.nombreTipo = nombreTipo;
	    }

	    // Getters y Setters
	    public int getIdTipoDeCuenta() {
	        return idTipoCuenta;
	    }

	    public void setIdTipoDeCuenta(int idTipoCuenta) {
	        this.idTipoCuenta = idTipoCuenta;
	    }

	    public String getNombreTipo() {
	        return nombreTipo;
	    }

	    public void setNombreTipo(String nombreTipo) {
	        this.nombreTipo = nombreTipo;
	    }

	    // Método toString() 
		@Override
		public String toString() {
			return "TipoDeCuenta [idTipoCuenta=" + idTipoCuenta + ", nombreTipo=" + nombreTipo + "]";
		}

	    

}
