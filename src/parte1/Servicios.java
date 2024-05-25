package parte1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Servicios {
	HashMap<String, Tarea> tareaMap;
	private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareasNoCriticas;
    //private 
	
	/*
	* Expresar la complejidad temporal del constructor.
	*/
	public Servicios(String pathProcesadores, String pathTareas) {
		tareaMap = new HashMap<>();
	}
	public HashMap<String, Tarea> getTareaMap() {
		return tareaMap;
	}
	
	public void addTarea(Tarea t) {
		tareaMap.put(t.getId(), t);
	}
	
	/*
	* Expresar la complejidad temporal del servicio 1.
	* LA COMPLEJIDAD ES DE O(1)
	*/
	public Tarea servicio1(String ID) {
		return tareaMap.get(ID);
	}
	
	/*
	* Expresar la complejidad temporal del servicio 2.
	*/
	public List<Tarea> servicio2(boolean esCritica){
		if (esCritica == true) {
			return tareasCriticas;
		} else {
			return tareasNoCriticas;
		}
	}
	
	/*
	* Expresar la complejidad temporal del servicio 3.
	*/
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> resultado = new LinkedList<>();
		
		return resultado;
	}
	
	
	
}
