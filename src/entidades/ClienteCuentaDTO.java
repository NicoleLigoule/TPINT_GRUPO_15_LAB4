package entidades;

import java.math.BigDecimal;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ClienteCuentaDTO {
	
	 private String nombre;
	    private String apellido;
	    private int numeroCuenta;
	    private String tipoCuenta;
	   //---- vista infoCliente
	    private String correoElectronico;
	    private String telefono;
	    private String direccion;
	    private BigDecimal saldoCtaCorriente;
	    private BigDecimal saldoCajaAhorro;
	    private Integer numeroCuentaCorriente;
	    private Integer numeroCajaAhorro;
	    private String CBU;
	    

	    // Getters y Setters
	    public String getNombre() { return nombre; }
	    public void setNombre(String nombre) { this.nombre = nombre; }

	    public String getApellido() { return apellido; }
	    public void setApellido(String apellido) { this.apellido = apellido; }

	    public int getNumeroCuenta() { return numeroCuenta; }
	    public void setNumeroCuenta(int numeroCuenta) { this.numeroCuenta = numeroCuenta; }

	    public String getTipoCuenta() { return tipoCuenta; }
	    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
	    
		public String getCorreoElectronico() { return correoElectronico; }
		public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
		
		public String getTelefono() { return telefono;	}
		public void setTelefono(String telefono) {	this.telefono = telefono; }
		
		public String getDireccion() {	return direccion; }
		public void setDireccion(String direccion) { this.direccion = direccion; }
		
		public BigDecimal getSaldoCtaCorriente() {	return saldoCtaCorriente;	}
		public void setSaldoCtaCorriente(BigDecimal saldoCtaCorriente) {this.saldoCtaCorriente = saldoCtaCorriente;	}
		
		public BigDecimal getSaldoCajaAhorro() { return saldoCajaAhorro; }
		public void setSaldoCajaAhorro(BigDecimal saldoCajaAhorro) { this.saldoCajaAhorro = saldoCajaAhorro; }
		
		public Integer getNumeroCuentaCorriente() {	return numeroCuentaCorriente; }		
		public void setNumeroCuentaCorriente(Integer numeroCuentaCorriente) { this.numeroCuentaCorriente = numeroCuentaCorriente; }
		
		public Integer getNumeroCajaAhorro() { return numeroCajaAhorro; }
		public void setNumeroCajaAhorro(Integer numeroCajaAhorro) {	this.numeroCajaAhorro = numeroCajaAhorro; }
		
		public String getCBU() {return CBU;	}
		public void setCBU(String cBU) {CBU = cBU; }
}
