package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class KMedias {

	private final float TOLERANCIA = (float) 0.01;
	private final int PESO_EXPONENCIAL = 2;
	private int nClases; 
	private ArrayList<String> nombreClase;
	/*
	 * U es la matriz con los grados de pertenencia a cada clase.
	 * Tiene tantas columnas como muestras haya y tantas filas como clases haya
	 * */
	private ArrayList<ArrayList<Float>> U; 
	//Son las vi
	private ArrayList<ArrayList<Float>> centros; 
	private ArrayList<ArrayList<Float>> centrosAnteriores; 
	private ArrayList<ArrayList<Float>> muestras;
	
	public KMedias() {
		this.nombreClase = new ArrayList<String>();
		this.leerFicheroMuestras();
		this.inicializarCentros();
		this.inicializarU();
		this.nClases = 2;
	}
	

	private void inicializarCentros() {
		this.centros = new ArrayList<ArrayList<Float>>();
		
		ArrayList<Float> v1 = new ArrayList<Float>();
		v1.add((float) 4.6);
		v1.add((float) 3.0);
		v1.add((float) 4.0);
		v1.add((float) 0.0);
		centros.add(v1);
		ArrayList<Float> v2 = new ArrayList<Float>();
		v2.add((float) 6.8);
		v2.add((float) 3.4);
		v2.add((float) 4.6);
		v2.add((float) 0.7);
		centros.add(v2);
	}
	
	private void inicializarU() {
		this.U = new ArrayList<ArrayList<Float>>();
		this.U.add(new ArrayList<Float>());
		this.U.add(new ArrayList<Float>());
		for(int i = 0; i < this.muestras.size(); i++) {
			this.U.get(0).add(CalculosKMedias.calcularPertenencia(0, this.centros, this.muestras.get(i)));
			this.U.get(1).add(CalculosKMedias.calcularPertenencia(1, this.centros, this.muestras.get(i)));
		}
		
	}
	
	private void leerFicheroMuestras() {
		this.muestras = new ArrayList<ArrayList<Float>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Iris2Clases.txt"));
			String line = br.readLine();
			for (int i = 0; i < 100 && line != null; i++) {
				String[] partes = line.split(",");
				ArrayList<Float> aux = new ArrayList<Float>();
				
				for(int j = 0; j < partes.length - 1; j++) {
					aux.add(Float.valueOf(partes[j]));
				}
				
				this.muestras.add(aux);
				
				String nombre = partes[partes.length - 1];
				boolean encontrado = false;
				
				for(int j = 0; j < this.nombreClase.size() && !encontrado; j++) {
					if(this.nombreClase.get(j).equals(nombre)) {
						encontrado = true;
					}
				} 
				if(!encontrado) 
					this.nombreClase.add(nombre);
				
				line = br.readLine();
			}	
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ejecutarAlgoritmo() {
		
		while(!criterioFinalizacion()) {
			this.actualizarCentros();
			this.actualizarPertenencias();
		}
	}
	
	private boolean criterioFinalizacion() {
		boolean terminar = false;
		if(this.centrosAnteriores != null) {
			terminar = (CalculosKMedias.distanciaEuclidea(this.centros.get(0), this.centrosAnteriores.get(0)) < this.TOLERANCIA) &&
					(CalculosKMedias.distanciaEuclidea(this.centros.get(1), this.centrosAnteriores.get(1)) < this.TOLERANCIA);
		} else {
			this.centrosAnteriores = new ArrayList<ArrayList<Float>>(this.centros);
		}
		return terminar;
	}
	
	private void actualizarPertenencias() {
		
		for(int i = 0; i < this.U.size(); i++) {
			this.U.get(0).set(i, CalculosKMedias.calcularPertenencia(0, this.centros, this.muestras.get(i)));
			this.U.get(1).set(i, CalculosKMedias.calcularPertenencia(1, this.centros, this.muestras.get(i)));
		}
	}
	
	private void actualizarCentros() {
		
		this.centrosAnteriores.set(0, this.centros.get(0));
		this.centrosAnteriores.set(1, this.centros.get(1)); 
		
		this.centros.set(0, CalculosKMedias.calcularCentro(0, centrosAnteriores.get(0), this.muestras, this.U));
		
		this.centros.set(1, CalculosKMedias.calcularCentro(1, centrosAnteriores.get(1), this.muestras, this.U));
	}
	
	public String test(String fichero) {
		String resultado = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			String line = br.readLine();
			String[] partes = line.split(",");
			
			ArrayList<Float> muestra = new ArrayList<Float>();
			
			for(int j = 0; j < partes.length - 1; j++) {
				muestra.add(Float.valueOf(partes[j]));
			}

			if(CalculosKMedias.calcularPertenencia(0, this.centros, muestra) > CalculosKMedias.calcularPertenencia(1, this.centros, muestra))
				resultado = this.nombreClase.get(0);
			else
				resultado = this.nombreClase.get(1);	
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
