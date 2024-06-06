package tpe;

public class Tarea {
	private String id;
	private String nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int nivelPrioridad;
    
	public Tarea(String id, String nombre, int tiempoEjecucion, boolean esCritica, int nivelPrioridad) {
		this.id = id;
		this.nombre = nombre;
		this.tiempoEjecucion = tiempoEjecucion;
		this.esCritica = esCritica;
		this.nivelPrioridad = nivelPrioridad;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public boolean isEsCritica() {
		return esCritica;
	}

	public int getNivelPrioridad() {
		return nivelPrioridad;
	}    
	
	@Override
	public String toString() {
	    return "\n Tarea{" +
	            "\n id='" + id + '\'' +
	            "\n nombre='" + nombre + '\'' +
	            "\n tiempoEjecucion=" + tiempoEjecucion +
	            "\n esCritica=" + esCritica +
	            "\n nivelPrioridad=" + nivelPrioridad +
	            "\n"+'}';
	}

	
}