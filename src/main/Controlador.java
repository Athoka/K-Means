package main;

import logica.KMedias;

public class Controlador {

	public Controlador() {
		// TODO Auto-generated constructor stub
	}

	public void ejecutar() {
	
		System.out.println("Se va a entrenar el sistema mediante el algoritmo K-medias");
		KMedias kmedias = new KMedias();
		kmedias.ejecutarAlgoritmo();
		System.out.println("Ha terminado el altoritmo K-medias");
		
		System.out.println("Ejecutando TestIris01");
		System.out.println("Pertenece a la clase " + kmedias.test("TestIris01.txt"));
		
		System.out.println("Ejecutando TestIris02");
		System.out.println("Pertenece a la clase " + kmedias.test("TestIris02.txt"));

		System.out.println("Ejecutando TestIris03");
		System.out.println("Pertenece a la clase " + kmedias.test("TestIris03.txt"));
		
	}	
}
