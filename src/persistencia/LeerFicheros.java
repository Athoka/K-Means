package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import logica.Clase;

public class LeerFicheros {

	public LeerFicheros() {}
	
	public HashMap<String, Clase> leerFicheroClases(String fichero) throws Exception {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			HashMap<String, Clase> hm = new HashMap<String, Clase>();
			
			
			
			String line = br.readLine();
			
			while (line != null) {
				String[] partes = line.split(",");
				ArrayList<Float> muestra = new ArrayList<Float>();
				for(int j = 0; j < partes.length - 1; j++) {
					muestra.add(Float.valueOf(partes[j]));
				}
				String nombre = partes[partes.length - 1];
				
				if(!hm.containsKey(nombre)) {
					Clase c = new Clase(nombre);
					hm.put(nombre, c);
				}

				hm.get(nombre).addMuestra(muestra);
				
				line = br.readLine();
			}
			
			br.close();
			return hm;
		} catch (Exception e) {
			throw new Exception();
		}
	}

	
	public void leerFicherosTest() {
		
	}
}
