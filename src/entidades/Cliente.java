package entidades;

import java.time.LocalDate;

public class Cliente {
	private String cuil;
	private int dni;
	private String nombre;
	private String apellido;
	private int id_sexo;
	private String id_nacionalidad;
	private LocalDate fechaNacimiento;
	private String direccion;
	private int id_localidad;
	private int id_provincia;
	private String correo;
	private String telefono;
	private boolean estado;
	
	public Cliente() {
		this.dni = 0;
	}
	
	public Cliente(String cuil, int dni, String nombre, String apellido, int id_sexo, String id_nacionalidad,
			LocalDate fechaNacimiento, String direccion, int id_localidad, int id_provincia, String correo, String telefono, boolean estado) {
		super();
		this.cuil = cuil;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id_sexo = id_sexo;
		this.id_nacionalidad = id_nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.id_localidad = id_localidad;
		this.id_provincia = id_provincia;
		this.correo = correo;
		this.telefono = telefono;
		this.estado = estado;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getId_sexo() {
		return id_sexo;
	}

	public void setId_sexo(int id_sexo) {
		this.id_sexo = id_sexo;
	}

	public String getId_nacionalidad() {
		return id_nacionalidad;
	}

	public void setId_nacionalidad(String id_nacionalidad) {
		this.id_nacionalidad = id_nacionalidad;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getId_localidad() {
		return id_localidad;
	}

	public void setId_localidad(int localidad) {
		this.id_localidad = localidad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [cuil=" + cuil + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", id_sexo="
				+ id_sexo + ", id_nacionalidad=" + id_nacionalidad + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", id_localidad=" + id_localidad + ", correo=" + correo + ", telefono="
				+ telefono + ", estado=" + estado + "]";
	}


}
