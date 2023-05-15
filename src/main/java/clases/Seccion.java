package clases;

public class Seccion {
	private int id;
	private String nombre;
	
	
	
	
	public Seccion(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Seccion() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Seccion [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
}
