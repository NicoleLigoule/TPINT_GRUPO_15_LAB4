package entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	    private BigDecimal saldo;	    
	    private String CBU;
	    private String localidadDescr;
	    private String provinciaDescr;
	    private String fechaNacimiento;
	    private String nacionalidad;
	    private String cuil;
	    private String dni;
	    
	    

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
		
		public String getCBU() {return CBU;	}
		public void setCBU(String cBU) {CBU = cBU; }
		
		public BigDecimal getSaldo() {  return saldo; }
		public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
		
		public String getLocalidadDescr() {
			return localidadDescr;
		}
		public void setLocalidadDescr(String localidadDescr) {
			this.localidadDescr = localidadDescr;
		}
		public String getProvinciaDescr() {
			return provinciaDescr;
		}
		public void setProvinciaDescr(String provinciaDescr) {
			this.provinciaDescr = provinciaDescr;
		}
		public String getFechaNacimiento() {
			return fechaNacimiento;
		}
		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
		public String getNacionalidad() {
			return nacionalidad;
		}
		public void setNacionalidad(String nacionalidad) {
			this.nacionalidad = nacionalidad;
		}
		public String getCuil() {
			return cuil;
		}
		public void setCuil(String cuil) {
			this.cuil = cuil;
		}
		public String getDni() {
			return dni;
		}
		public void setDni(String dni) {
			this.dni = dni;
		}
		
		
}
