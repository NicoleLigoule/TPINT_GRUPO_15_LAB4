package daoImpl;

public class ClienteCuentaDTO {
	
	 private String nombre;
	    private String apellido;
	    private int numeroCuenta;
	    private String tipoCuenta;

	    // Getters y Setters
	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }

	    public String getApellido() { return apellido; }
	    public void setApellido(String apellido) { this.apellido = apellido; }

	    public int getNumeroCuenta() { return numeroCuenta; }
	    public void setNumeroCuenta(int numeroCuenta) { this.numeroCuenta = numeroCuenta; }

	    public String getTipoCuenta() { return tipoCuenta; }
	    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
}
