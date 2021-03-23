package com.packagetondeuse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class CalculPositionsTondeuses {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// Initialiser le terrain 1
		Terrain terrain1 = new Terrain(0, 0);
		terrain1.setXterrain(RecupXterrain(RecupLigne(1)));
		terrain1.setYterrain(RecupYterrain(RecupLigne(1)));
		// System.out.println("Terrain : " + "X = " + terrain1.getXterrain() + " Y = " +
		// terrain1.getYterrain());

		// Trouver le nombre de tondeuses
		BufferedReader R = new BufferedReader(new FileReader(
				"C:\\Users\\maria\\Documents\\Emploi\\Formations\\Java\\Projet tondeuse\\A lire\\Consigne.txt"));

		String ligne2;
		int NombreDeLignes = 0;
		int NTondeuses = 0;
		while ((ligne2 = R.readLine()) != null) {

			// System.out.println(ligne2);
			NombreDeLignes = NombreDeLignes + 1;
		}
		NTondeuses = (NombreDeLignes - 1) / 2;
		R.close();

		// Initialiser la position des tondeuses

		Tondeuse[] T = new Tondeuse[NTondeuses];
		for (int i = 1; i <= NTondeuses; i++) {

			T[i - 1] = new Tondeuse(0, 0, "N", 0, 0);
			T[i - 1].setXdep(RecupXdep(RecupLigne(i * 2)));
			T[i - 1].setYdep(RecupYdep(RecupLigne(i * 2)));
			T[i - 1].setOrientationDep(RecupOrientationDep(RecupLigne(i * 2)));
			// System.out.println("Valeur de la case " + i + " du tableau " + T[i - 1]);
		}
		// Tondeuse tondeuse1 = new Tondeuse(0, 0, "N", 0, 0);
		// tondeuse1.setXdep(RecupXdep(RecupLigne(2)));
		// tondeuse1.setYdep(RecupYdep(RecupLigne(2)));
		// tondeuse1.setOrientationDep(RecupOrientationDep(RecupLigne(2)));

		// Exécuter la commande et renvoyer la position finale

		for (int j = 1; j <= NTondeuses; j++) {
			String Orientation;
			Orientation = RecupOrientationDep(RecupLigne(j * 2));
			String[] Tableau = { "N", "E", "S", "W" };
			int Index;
			int x;
			int y;
			int X;
			int Y;
			x = T[j - 1].getXdep();
			y = T[j - 1].getYdep();
			T[j - 1].setX(x);
			T[j - 1].setY(y);
			X = T[j - 1].getX();
			Y = T[j - 1].getY();

			for (int i = 0; i < RecupLigne(j * 2 + 1).length(); i++) {
				String Caractere = RecupCommandeStep(RecupLigne(j * 2 + 1), i);
				// System.out.println(Caractere);
				// System.out.println("Orientation = " + Orientation);
				Index = Arrays.asList(Tableau).indexOf(Orientation);
				// System.out.println("Index = " + Index);
				// System.out.println("xdep = " + x);
				// System.out.println("ydep = " + y);
				// System.out.println("X = " + X);
				// System.out.println("Y = " + Y);

				switch (Caractere) {

				case "D":

					if (Orientation.equals("W")) {
						Orientation = "N";
					} else {

						Orientation = Tableau[Index + 1];
						// System.out.println("Tourne à droite");
						break;
					}
					break;

				case "G":
					if (Orientation.equals("N")) {

						Orientation = "W";
					} else {
						Orientation = Tableau[Index - 1];
						// System.out.println("Tourne à gauche");
						break;
					}
					break;

				case "A":

					switch (Orientation) {

					case "N":

						if (Y + 1 <= terrain1.getYterrain()) {
							Y = T[j - 1].getY() + 1;
							T[j - 1].setY(Y);
							// System.out.println("A avancé vers le nord");
							// System.out.println(" X = " + T[j - 1].getX());
							// System.out.println(" Y = " + T[j - 1].getY());

						}
						break;

					case "E":

						if (X + 1 <= terrain1.getXterrain()) {
							X = T[j - 1].getX() + 1;
							T[j - 1].setX(X);
							// System.out.println("A avancé vers l'est");
							// System.out.println(" X = " + T[j - 1].getX());
							// System.out.println(" Y = " + T[j - 1].getY());
						}

						break;

					case "S":

						if (Y - 1 >= 0) {
							Y = T[j - 1].getY() - 1;
							T[j - 1].setY(Y);
							// System.out.println("A avancé vers le sud");
							// System.out.println(" X = " + T[j - 1].getX());
							// System.out.println(" Y = " + T[j - 1].getY());
						}

						break;

					case "W":

						if (X - 1 >= 0) {
							X = T[j - 1].getX() - 1;
							T[j - 1].setX(X);
							// System.out.println("A avancé vers l'ouest");
							// System.out.println(" X = " + T[j - 1].getX());
							// System.out.println(" Y = " + T[j - 1].getY());
						}

						break;

					}
					break;

				default:

					System.out.println("Hors cas AGD");
					break;

				}
				// System.out.println(T[j - 1].getX() + " " + T[j - 1].getY() + " " +
				// Orientation);

			}
			System.out.println(T[j - 1].getX() + " " + T[j - 1].getY() + " " + Orientation);
		}

	}

	public static String RecupLigne(int NumLigne) throws Exception {

		BufferedReader r = new BufferedReader(new FileReader(
				"C:\\Users\\maria\\Documents\\Emploi\\Formations\\Java\\Projet tondeuse\\A lire\\Consigne.txt"));

		String ligne = "";

		for (int i = 1; i <= NumLigne; i++) {

			if (i == NumLigne) {

				ligne = r.readLine();

			}

			else {
				r.readLine();
			}

		}
		r.close();
		return ligne;

	}

	public static int RecupXdep(String PositionDepart) throws Exception {

		String C = PositionDepart.substring(0, 1);
		int B = Integer.parseInt(C);
		return B;
	}

	public static int RecupYdep(String PositionDepart) throws Exception {

		String C = PositionDepart.substring(2, 3);
		int B = Integer.parseInt(C);

		return B;
	}

	public static String RecupOrientationDep(String PositionDepart) throws Exception {

		String C = PositionDepart.substring(4, 5);

		return C;
	}

	public static int RecupXterrain(String Taille) throws Exception {

		String C = Taille.substring(0, 1);
		int B = Integer.parseInt(C);

		return B;
	}

	public static int RecupYterrain(String Taille) throws Exception {

		String C = Taille.substring(2, 3);
		int B = Integer.parseInt(C);

		return B;
	}

	public static String RecupCommandeStep(String Commande, int Curseur) throws Exception {

		String C = Commande.substring(Curseur, Curseur + 1);

		return C;
	}

}
