package programme;

import java.util.Scanner;

import affichage.Fenetre;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		System.out.println("Entrez le dossier � charger \n le dossier ne doit comporter que des donn�es OSM au format json \n \"N\" pour ignorer");
		String path = sc.nextLine();
		if (path.equals("N") || path.equals("n")) {
			Fenetre fen = new Fenetre();
		}
		else {
			Fenetre fen = new Fenetre(path);
		}
	}
}
