package entidades;

public class Localidad {
	private int id_localidad;
	private int id_provincia;
	private String nombreLoca;
	
	public Localidad() {
		
	}
	
	public Localidad(int id_localidad, int id_provincia, String nombreLoca) {
		super();
		this.id_localidad = id_localidad;
		this.id_provincia = id_provincia;
		this.nombreLoca = nombreLoca;
	}

	public int getId_localidad() {
		return id_localidad;
	}

	public void setId_localidad(int id_localidad) {
		this.id_localidad = id_localidad;
	}

	public int getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(int id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getNombreLoca() {
		return nombreLoca;
	}

	public void setNombreLoca(String nombreLoca) {
		this.nombreLoca = nombreLoca;
	}
	
	

}
