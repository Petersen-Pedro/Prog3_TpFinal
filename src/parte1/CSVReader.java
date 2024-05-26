package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVReader {
	
	
	public HashMap<String, Tarea> LectorTareasCSV(String pathTareas){
		
		ArrayList<String[]> contenidoPathTareas = this.leerCSV(pathTareas);
		HashMap<String, Tarea> contenidoTareas = new HashMap<String, Tarea>();
		
		for(String[] t : contenidoPathTareas) {
			String id = t[0];
			String nombre = t[1];
			Integer tiempoEjecucion = Integer.parseInt(t[2]);
			Boolean esCritica = Boolean.parseBoolean(t[3]);
			Integer nivelPrioridad = Integer.parseInt(t[4]);
			Tarea tarea = new Tarea(id, nombre, tiempoEjecucion, esCritica, nivelPrioridad);
			contenidoTareas.put(id, tarea);
		}
		
		return null;
	}

	private ArrayList<String[]> leerCSV(String path) {
		ArrayList<String[]> contenido = new ArrayList<String[]>();
		
		File archivo = new File(path);
		FileReader archivoLector = null;
		BufferedReader lineaLector = null;
		
		try {
			archivoLector = new FileReader(archivo);
			lineaLector = new BufferedReader(archivoLector);
			String linea = null;
			while((linea = lineaLector.readLine()) != null) {
				linea = linea.trim();
				contenido.add(linea.split(";"));
			}
		} catch(Exception e ) {
			
		}
		
		return contenido;
	}
}
