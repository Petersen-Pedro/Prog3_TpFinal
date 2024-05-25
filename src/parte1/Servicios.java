package parte1;

import java.util.HashMap;

public class Servicios {
	HashMap<String, Tarea> tareaMap;
	
	public Servicios() {
		tareaMap = new HashMap<>();
	}
	
	public void addTarea(Tarea t) {
		tareaMap.put(t.getId(), t);
	}
	
	public Tarea servicio1(String id) {
		return tareaMap.get(id);
	}

	public HashMap<String, Tarea> getTareaMap() {
		return tareaMap;
	}
	
	
	
}
