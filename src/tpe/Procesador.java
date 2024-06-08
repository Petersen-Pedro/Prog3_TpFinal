package tpe;

import java.util.LinkedList;


public class Procesador {
	private String id;
	private String codigo;
	private boolean estaRefrigerado;
	private int anioFuncionamiento;
    private LinkedList<Tarea> tareas;
    private int tiempoDeEjecucionAcumulado;
    private int tareasCriticas;
	
	public Procesador(  String id, String codigo, boolean estaRefrigerado, 
						int anioFuncionamiento) {
		this.id = id;
		this.codigo = codigo;
		this.estaRefrigerado = estaRefrigerado;
		this.anioFuncionamiento = anioFuncionamiento;
        this.tareas = new LinkedList<>();
        this.tiempoDeEjecucionAcumulado = 0;
        this.tareasCriticas = 0;
	}

	public LinkedList<Tarea> getTareas() {
        return tareas;
    }

    public int getTiempoDeEjecucionAcumulado() {
        return tiempoDeEjecucionAcumulado;
    }

    public void agregarTarea(Tarea tarea) {
        this.tareas.add(tarea);
        if(tarea.isEsCritica()) {
        	this.setTareasCriticas(tareasCriticas+1);
        }
        this.tiempoDeEjecucionAcumulado += tarea.getTiempoEjecucion();
    }

	public void setTareasCriticas(int tareasCriticas) {
		this.tareasCriticas = tareasCriticas;
	}

	public void eliminarTarea(Tarea tarea) {
		 if(tarea.isEsCritica()) {
	        	this.setTareasCriticas(tareasCriticas-1);
	        }
        this.tareas.remove(tarea);
        this.tiempoDeEjecucionAcumulado -= tarea.getTiempoEjecucion();
    }
	
	public int getTareasCriticas() {
		return tareasCriticas;
	}

	// Método para realizar una copia profunda de un procesador
    public Procesador copiar() {
        Procesador copia = new Procesador(this.id,this.codigo,this.estaRefrigerado,this.anioFuncionamiento);
        copia.tiempoDeEjecucionAcumulado = this.tiempoDeEjecucionAcumulado;
        copia.tareas = new LinkedList<>(this.tareas);
        copia.tareasCriticas = this.tareasCriticas;
        return copia;
    }

	public String getId() {
		return id;
	}


	public String getCodigo() {
		return codigo;
	}


	public boolean isEstaRefrigerado() {
		return estaRefrigerado;
	}


	public void setEstaRefrigerado(boolean estaRefrigerado) {
		this.estaRefrigerado = estaRefrigerado;
	}

	public int getAnioFuncionamiento() {
		return anioFuncionamiento;
	}
	
	
}
