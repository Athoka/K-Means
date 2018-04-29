package logica;

import java.util.ArrayList;

public class Clase {

	private ArrayList<ArrayList<Float>> muestras;
	private String nombreClase;
	private int numeroMuestras;
	private int dimension;
	
	public Clase() {
		// TODO Auto-generated constructor stub
	}
	
	public Clase(String nombre) {
		this.nombreClase = nombre;
		this.muestras = new ArrayList<ArrayList<Float>>();
	}
	
	public void addMuestra(ArrayList<Float> m) {
		this.muestras.add(m);
	}

}
