package main;

import persistencia.LeerFicheros;

public class Main {


	public static void main(String[] args) {
		LeerFicheros l = new LeerFicheros();
		try {
			l.leerFicheroClases("Iris2Clases.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
