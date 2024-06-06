package tpe.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import tpe.Servicios;
import tpe.Tarea;

public class CSVReader {
	
	public CSVReader() {
	}
	
	ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
	public HashMap<String, Tarea> readTasks(String taskPath, Servicios servicios){
		
		HashMap<String, Tarea> contenidoTareas = new HashMap<String, Tarea>();
		
		
		ArrayList<String[]> lines = this.readContent(taskPath);
		
		for(String[] t : lines) {
			String id = t[0];
			String nombre = t[1];
			Integer tiempoEjecucion = Integer.parseInt(t[2]);
			Boolean esCritica = Boolean.parseBoolean(t[3]);
			Integer nivelPrioridad = Integer.parseInt(t[4]);

			Tarea tarea = new Tarea(id, nombre, tiempoEjecucion, 
									esCritica, nivelPrioridad);
			contenidoTareas.put(id, tarea);
			servicios.insertCritica(tarea);
			servicios.addTareaPrioridad(tarea);
			
			
		}
		
		return contenidoTareas;
	}
	
}
