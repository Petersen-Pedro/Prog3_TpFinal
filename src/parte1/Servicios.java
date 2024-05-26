package parte1;

import java.util.ArrayList;
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
		this.LectorTareasCSV(pathTareas);
				
	}
		
	
	/*
	* Complejidad temporal = O(1).
	* HashMap nos permite construir un mapa de busqueda, que al 
	* darle la key correspondiente (el id en este caso) nos da 
	* la ubicacion de lo que buscamos
	*/
	public Tarea servicio1(String id) {
		return this.tareaMapId.get(id);
	}
	
	/*
	* Complejidad temporal = O(1).
	* Las listas correspondientes ya existen, solo hace falta 
	* que retorne la correspondiente
	*/
	public List<Tarea> servicio2(boolean esCritica){
		return esCritica ? tareasCriticas : tareasNoCriticas;
		/*
		if (esCritica == true) {
			return tareasCriticas;
		} else {
			return tareasNoCriticas;
		}*/
	}
	
	/*
	* Complejidad temporal = O(m log n).
	* n = niveles totales de prioridad
	* m = total de tareas dentro del rango establecido
	* 
	*/
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> resultado = new LinkedList<>();
		
		for (Map.Entry<Integer, LinkedList<Tarea>> nodoPrioridad : tareasPrioridad.subMap(prioridadInferior, true, prioridadSuperior, true).entrySet()) {
			resultado.addAll(nodoPrioridad.getValue());
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
	
	

	//public HashMap<String, Tarea> getTareaMapId() {
	//	return tareaMapId;
	//}
	public void insertCritica(Tarea t) {
		if (t.isEsCritica() == true) {
			tareasCriticas.add(t);
		} else {
			tareasNoCriticas.add(t);
		}
	}
	public void addTarea(Tarea t) {
		tareaMapId.put(t.getId(), t);
	}
	
	public TreeMap<Integer, LinkedList<Tarea>> getTareasPrioridad() {
		return tareasPrioridad;
	}
	
	public void addTareaPrioridad(Tarea t) {
		tareasPrioridad.putIfAbsent(t.getNivelPrioridad(), new LinkedList<>());
		tareasPrioridad.get(t.getNivelPrioridad()).add(t);
	}
	
	//------------
	
	//2:42am
	//EN UN PRICIPIO PUSE ESTE METODO EN EL CSVReader, PERO ERA MAS COMODO HACER QUE LAS TAREAS SE INSERTEN EN EL FOR
	public HashMap<String, Tarea> LectorTareasCSV(String pathTareas){
	
		CSVReader lector = new CSVReader();
		ArrayList<String[]> contenidoPathTareas = lector.leerCSV(pathTareas);
		HashMap<String, Tarea> contenidoTareas = new HashMap<String, Tarea>();
		
		for(String[] t : contenidoPathTareas) {
			String id = t[0];
			String nombre = t[1];
			Integer tiempoEjecucion = Integer.parseInt(t[2]);
			Boolean esCritica = Boolean.parseBoolean(t[3]);
			Integer nivelPrioridad = Integer.parseInt(t[4]);
			Tarea tarea = new Tarea(id, nombre, tiempoEjecucion, esCritica, nivelPrioridad);
			//contenidoTareas.put(id, tarea);
			this.addTarea(tarea);
			insertCritica(tarea);
			addTareaPrioridad(tarea);
			
		}
		
		return contenidoTareas;
	}
	
	
	
}
