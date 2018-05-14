package logica;

import java.util.ArrayList;

public class CalculosKMedias {

	//Esto hace cálculos solamente
	
	private static final int B = 2;
	
	public CalculosKMedias() {
		
	}

	public static ArrayList<Float> calcularCentro(int c, ArrayList<Float> v, ArrayList<ArrayList<Float>> x, ArrayList<ArrayList<Float>> u) {
		
		ArrayList<Float> centroNuevo = new ArrayList<Float>(x.get(0));
		float a = 0, den = 0, aux = 0;
		
		for(int i = 0; i < x.size(); i++) { //Para todas las muestras en x
			den = den + (float) Math.pow(u.get(c).get(i), B); //Para la muestra i
			for(int j = 0; j < x.get(i).size(); j++) { //Para todas las componentes de x
				a = centroNuevo.get(j);
				a = a + (float) Math.pow(u.get(c).get(i), B) * x.get(i).get(j);
				centroNuevo.set(j, a);
			}
		
		}
		
		for(int i = 0; i < centroNuevo.size(); i++) {
			aux = centroNuevo.get(i);
			aux = aux / den;
			centroNuevo.set(i, aux);
		}
		

		return centroNuevo;
	}
	
	public static float calcularPertenencia(int claseActual, ArrayList<ArrayList<Float>> centros, ArrayList<Float> muestra) {
		float r = 0;
		float numerador, denominador = 0;
		
		numerador = (float) (1/Math.pow((distanciaEuclidea(muestra, centros.get(claseActual))), (1/B-1)));
		
		for(int i = 0; i < 2; i++) { //2 clases
			denominador = denominador + (float) Math.pow(1/(distanciaEuclidea(muestra, centros.get(i))), (1/B-1));
		}
		
		
		r = numerador / denominador;
		
		return r;
	}
	
	public static float distanciaEuclidea(ArrayList<Float>a, ArrayList<Float> b) {
		float r = 0;
		
		r = (float) Math.sqrt(distanciaEuclideaAlCuadrado(a, b));
		
		return r;
	}
	
	public static float distanciaEuclideaAlCuadrado(ArrayList<Float>a, ArrayList<Float> b) {
		float r = 0;
		
		for(int i = 0; i < a.size(); i++) {
			r =  r + (float) Math.pow((a.get(i) - b.get(i)), 2);
		}
		
		return r;
	}
}
