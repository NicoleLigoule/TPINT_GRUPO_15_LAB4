package entidades;

public class Usuario {

	    private String cuilUs;            
	    private String usuarioUs;         
	    private String contraseniaUs;     
	    private boolean rolUs;            
	    private boolean estadoUs;         
	     
	    // Constructores
	    public Usuario() {
	    	this.estadoUs = true;  // Estado por defecto 
	    }


	    public Usuario(String cuilUs, String usuarioUs, String contraseniaUs, boolean rolUs, boolean estadoUs) {
	        this.cuilUs = cuilUs;
	        this.usuarioUs = usuarioUs;
	        this.contraseniaUs = contraseniaUs;
	        this.rolUs = rolUs;
	        this.estadoUs = estadoUs;
	    }

	    // Getters y setters
	    public String getCuilUs() {
	        return cuilUs;
	    }

	    public void setCuilUs(String cuilUs) {
	        this.cuilUs = cuilUs;
	    }

	    public String getUsuarioUs() {
	        return usuarioUs;
	    }

	    public void setUsuarioUs(String usuarioUs) {
	        this.usuarioUs = usuarioUs;
	    }

	    public String getContraseniaUs() {
	        return contraseniaUs;
	    }

	    public void setContraseniaUs(String contraseniaUs) {
	        this.contraseniaUs = contraseniaUs;
	    }

	    public boolean isRolUs() {
	        return rolUs;
	    }

	    public void setRolUs(boolean rolUs) {
	        this.rolUs = rolUs;
	    }

	    public boolean isEstadoUs() {
	        return estadoUs;
	    }

	    public void setEstadoUs(boolean estadoUs) {
	        this.estadoUs = estadoUs;
	    }


	    // Override toString()
		@Override
		public String toString() {
			return "Usuario [cuilUs=" + cuilUs + ", usuarioUs=" + usuarioUs + ", contraseniaUs=" + contraseniaUs
					+ ", rolUs=" + rolUs + ", estadoUs=" + estadoUs + "]";
		}
}
	    

