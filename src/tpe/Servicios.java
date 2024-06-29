package tpe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import tpe.utils.Backtracking_Greedy;
import tpe.utils.CSVReader;

public class Servicios {
	private HashMap<String, Tarea> tareaMapId;
	private LinkedList<Procesador> procesadores;
	private LinkedList<Tarea> tareasCriticas;
    private LinkedList<Tarea> tareasNoCriticas;
    private TreeMap<Integer, LinkedList<Tarea>> tareasPrioridad; 
	
	/*
	* Complejidad temporal = O(n+m).
	* n = Cantidad de lineas del archivo de procesadores
	* m = Cantidad de lineas en el archivo de tareas
	*/
	public Servicios(String pathProcesadores, String pathTareas) {
		tareasCriticas = new LinkedList<>();
		tareasNoCriticas = new LinkedList<>();
		tareasPrioridad = new TreeMap<>();
		CSVReader reader = new CSVReader();
		this.tareaMapId = reader.readTasks(pathTareas, this);
		this.procesadores = reader.readProcessors(pathProcesadores,this);		
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
	}
	
	/*
	* Complejidad temporal = O(m log n).
	* n = niveles totales de prioridad
	* m = total de tareas dentro del rango establecido
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

	
	public LinkedList<Procesador> servicio4Backtraking(int tiempoMaxProcNoRefrigerado) {
		
		LinkedList<Tarea> tareas = new LinkedList<>();
		tareas.addAll(tareasCriticas);
		tareas.addAll(tareasNoCriticas);
        Backtracking_Greedy servicio4 = new Backtracking_Greedy();
        servicio4.back(tareas, this.procesadores, tiempoMaxProcNoRefrigerado);
        System.out.println("El peor tiempo es "+servicio4.getPeorTiempo());
		System.out.println("la cant de estados es "+servicio4.cantidadDeEstados());
        return servicio4.back(tareas, this.procesadores, tiempoMaxProcNoRefrigerado);
    }
	
	public LinkedList<Procesador> servicio4Greedy(int tiempoMaxProcNoRefrigerado) {
		LinkedList<Tarea> tareas = new LinkedList<>();
		tareas.addAll(tareasCriticas);
		tareas.addAll(tareasNoCriticas);
        Backtracking_Greedy servicio4 = new Backtracking_Greedy();
        servicio4.greedy(tareas, this.procesadores, tiempoMaxProcNoRefrigerado);
        System.out.println("El peor tiempo es "+servicio4.getPeorTiempo());
		System.out.println("la cant de estados es "+servicio4.cantidadDeEstados());
        return servicio4.greedy(tareas, this.procesadores, tiempoMaxProcNoRefrigerado);
    }
	/*
	 */
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
	public void addProcesadores(Procesador p) {
		this.procesadores.add(p);
	}
		
}
