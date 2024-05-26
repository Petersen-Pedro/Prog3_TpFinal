package parte1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servicios {
	private HashMap<String, Tarea> tareaMapId;
	private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareasNoCriticas;
    private TreeMap<Integer, LinkedList<Tarea>> tareasPrioridad; 
	
	/*
	* Complejidad temporal = O(n+m).
	* n = Cantidad de lineas del archivo de procesadores
	* m = Cantidad de lineas en el archivo de tareas
	*/
	public Servicios(String pathProcesadores, String pathTareas) {
		tareaMapId = new HashMap<>();
		tareasCriticas = new LinkedList<>();
		tareasNoCriticas = new LinkedList<>();
		tareasPrioridad = new TreeMap<>();		
				
		cargarDatos(pathProcesadores, pathTareas);
	}
	
	private void cargarDatos(String pathProcesadores, String pathTareas) {
		
		CSVReader lector = new CSVReader(new FileReader(pathTareas));
		
	}
	
	
	
	
	
	/*
	* Complejidad temporal = O(1).
	* HashMap nos permite construir un mapa de busqueda, que al 
	* darle la key correspondiente (el id en este caso) nos da 
	* la ubicacion de lo que buscamos
	*/
	public Tarea servicio1(String ID) {
		return this.getTareaMapId().get(ID);
	}
	
	/*
	* Complejidad temporal = O(1).
	* Las listas correspondientes ya existen, solo hace falta 
	* que retorne la correspondiente
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
		
		for (Map.Entry<Integer, LinkedList<Tarea>> entry : tareasPrioridad.subMap(prioridadInferior, true, prioridadSuperior, true).entrySet()) {
			resultado.addAll(entry.getValue());
		}
				 
		/* 
		 	.subMap = Nos permite crear un subconjunto de elementos 
					a partir del mapa principal	que se encuentren 
					dentro del rango solicitado con prioridadInferior 
					y prioridadSuperior.
					Gracias a los `true`, aclaramos que prioridadInferior 
					y prioridadSuperior deben de ser contados tambien.
		*/
				
		return resultado;
	}
	
	

	public HashMap<String, Tarea> getTareaMap() {
		return getTareaMapId();
	}
	public void addTarea(Tarea t) {
		getTareaMapId().put(t.getId(), t);
	}
	public TreeMap<Integer, LinkedList<Tarea>> getTareasPrioridad() {
		return tareasPrioridad;
	}

	public HashMap<String, Tarea> getTareaMapId() {
		return tareaMapId;
	}
	
}
