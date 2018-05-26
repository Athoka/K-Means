package main;

import java.util.Scanner;

import logica.KMedias;

public class Main {


	public static void main(String[] args) {
		int opt = -1;
		while(opt != 0) {
			
			Scanner entrada = new Scanner(System.in);
			System.out.println("1 - Ejecutar Algoritmo");
			System.out.println("0 - Salir");
			System.out.print("Introduce opción: ");
			opt = entrada.nextInt();
			
			if(opt==1) {
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
		
	}

}
