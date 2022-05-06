package facu.catriel.parcial.primer;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		
		// Creamos un objeto lectoescritor, para leer y escribir los archivos necesarios
		Lectoescritura l1 = new Lectoescritura("src/archivos/robot.in", "src/archivos/robot.out");
	
		// Utilizamos un ArrayList para almacenar las dos líneas pertenecientes al archivo de lectura
		ArrayList<String> arrStrings = new ArrayList<>(l1.leerArchivo());
		
		// El try-catch realiza un manejo de errores en el caso que el archivo de entrada no cumpla las condiciones
		try {
			// Si el formato del archivo no es correcto, lanza una excepción y el programa termina
			if(arrStrings.size() < 3) {
				
				// Separamos las líneas del array de líneas
				String linea1 = arrStrings.get(0);
				String linea2 = arrStrings.get(1);
				
				// Separamos los datos en un array de Strings
				
				String[] datosSeparados = linea1.split(" ");

				
				// Acá se lanzan las excepciones para que el programa no termine de forma brusca
				if(datosSeparados.length > 5) throw new Exception("El formato del archivo es incorrecto");
		
				if(linea2.length() % 2 != 0) throw new Exception("El formato del archivo es incorrecto");
				
				// Asignamos los valores iniciales del programa ya que esta primer línea siempre tiene el mismo tamaño
				
				int x = Integer.parseInt(datosSeparados[0]);
				int y = Integer.parseInt(datosSeparados[1]);
				int qColumnas = Integer.parseInt(datosSeparados[3]);
				int qFilas = Integer.parseInt(datosSeparados[4]);
				
				char orientacion = linea1.charAt(4);
				
				// Acá verificamos que el tamaño de la cuadrícula sea mayor a 0 y menor o igual a 100
				if(qColumnas <= 100 && qFilas <= 100 && 0 < qColumnas && 0 < qFilas) {

					// Creamos la cuadrícula
					ArrayList<ArrayList<Integer>> arrXY = new ArrayList<>(qFilas);

					for(int j = 0; j < qFilas; j++) {
						arrXY.add(new ArrayList<>());
					} // Complejidad O(n)
						
					for (int i = 0; i < qFilas; i++) {
						    
						for (int j = 1; j <= qColumnas; j++) {
							arrXY.get(i).add(j);
						}
					} // Complejidad O(n^2)
						
					// Separamos las instrucciones en pares
					ArrayList<String> instrucciones = new ArrayList<String>();
					
					for(int i = 0; i < linea2.length(); i += 2) {
						// Verificamos que la orden sea válida, si no lo es, no se añade al array de instrucciones
						if(linea2.charAt(i) == 'A' || linea2.charAt(i) == 'R') {
							instrucciones.add(linea2.substring(i, i+2));
						}
					} // Complejidad O(n)

					// Verificamos que las instrucciones no superen el máximo, si sucede lanza una excepción
					if(instrucciones.size() <= 125) {
					
						Robot r1 = new Robot(orientacion, x, y, arrXY.size(), arrXY.get(0).size());

						// El robot ejecuta las órdenes según aparecen en el arraylist de instrucciones
						for(int i = 0; i < instrucciones.size(); i++) {
							char orden = instrucciones.get(i).charAt(0);
							int numero = Integer.parseInt(instrucciones.get(i).substring(1, 2));
							
							// Verifica qué tipo de orden es
							if(orden == 'A') {
								r1.avanzar(numero);

							} else if(orden == 'R') {
								r1.orientarse(numero);
							}
						} // Complejidad O(n)
						
						// Se guarda la última posición del robot para escribirla dentro del archivo de salida
						String posFinal = r1.getPosRobot();
					
						// Se escribe el archivo de salida
						l1.escribirArchivo(posFinal);
						
					} else throw new ExceptionLimite("El máximo de órdenes es de 125");
				} else throw new ExceptionLimite("El máximo de celdas es de 100 x 100 con al menos 1 columna y fila");
			} else throw new Exception("El formato del archivo es incorrecto");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			l1.escribirArchivo(e.getMessage());
	}
	}
}
