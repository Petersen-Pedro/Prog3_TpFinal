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
	    return "\n \t Tarea{" +
	            "\n \t id='" + id + '\'' +
	            "\n \t nombre='" + nombre + '\'' +
	            "\n \t tiempoEjecucion=" + tiempoEjecucion +
	            "\n \t esCritica=" + esCritica +
	            "\n \t nivelPrioridad=" + nivelPrioridad +
	            "\n"+'}';
	}

	
}
