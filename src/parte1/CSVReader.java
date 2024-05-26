package parte1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVReader {

	//"Viva la Santa Stackoverflow, mueran los salvajes ChatGPTros"
	
	ArrayList<String[]> leerCSV(String path) {
		ArrayList<String[]> contenido = new ArrayList<String[]>();
		
		//File archivo = new File(path);
		//FileReader archivoLector = null;
		//BufferedReader lineaLector = null;
		File archivo = new File(path);
		
		try (FileReader archivoLector = new FileReader(archivo);
			BufferedReader lineaLector = new BufferedReader(archivoLector);){
			//archivoLector = new FileReader(archivo);
			//lineaLector = new BufferedReader(archivoLector);
			//String linea = null;
			String linea;
			while((linea = lineaLector.readLine()) != null) {
				linea = linea.trim();
				contenido.add(linea.split(";"));
			}
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
		return contenido;
	}
}
