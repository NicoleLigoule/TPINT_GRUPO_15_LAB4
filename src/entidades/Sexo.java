package entidades;

public class Sexo {
	private int id_sexo;
	private String descripcion;
	
	public Sexo() {
		
	}
	
	public Sexo(int id_sexo, String descripcion) {
		super();
		this.id_sexo = id_sexo;
		this.descripcion = descripcion;
	}
	
	public int getId_sexo() {
		return id_sexo;
	}
	public void setId_sexo(int id_sexo) {
		this.id_sexo = id_sexo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
