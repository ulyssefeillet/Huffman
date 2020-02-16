package huffman;

import java.util.ArrayList;

//Arbre binaire de fréquences qu'on insère dans une liste

public class ArbreBinFreq {
	private CarFreq valeur = null;
	private ArbreBinFreq gauche = null;
	private ArbreBinFreq droit = null;
	private boolean vide = true;

	public ArbreBinFreq(CarFreq x) {
		this.valeur = x;
		vide = false;
	}

	public ArbreBinFreq(String chaine, int frequence) {
		this.valeur = new CarFreq(chaine, frequence);
		vide = false;
	}
	
	public ArbreBinFreq() {
		vide = true;
	}

	public ArbreBinFreq(CarFreq x, ArbreBinFreq g, ArbreBinFreq d) {
		this.valeur = x;
		this.gauche = g;
		this.droit = d;
		vide = false;
	}

	public CarFreq racine() {
		return valeur;
	}

	public boolean vide() {
		return vide;
	}

	public ArbreBinFreq Gauche() {
		return gauche;
	}

	public ArbreBinFreq Droit() {
		return droit;
	}

	public void afficher() {
		if (vide == true)  {
			System.out.println("[]");
			return;
		}
			
		System.out.print("[R:" + this.racine());
		if (this.gauche != null) {
			System.out.print(",G:");
			this.Gauche().afficher();
		}
		if (this.droit != null) {
			System.out.print(",D:");
			this.Droit().afficher();
		}
		System.out.print("]");
	}

	public boolean contenue(ListeArbreBinFreq uneListe, ArbreBinFreq ab) {
		if (uneListe.vide()) {
			return true;
		} else if (ab.vide()) {
			return false;
		} else if (ab.racine() == uneListe.tete().valeur) {
			return contenue(uneListe.reste(), ab.gauche) || contenue(uneListe.reste(), ab.droit);
		} else {
			return contenue(uneListe, ab.gauche) || contenue(uneListe, ab.droit);
		}
	}

	public static void main(String[] args) {

		ListeArbreBinFreq L = new ListeArbreBinFreq();
		ListeArbreBinFreq L2 = new ListeArbreBinFreq();
		ArbreBinFreq ab = new ArbreBinFreq();
		ArbreBinFreq ab2 = new ArbreBinFreq(new CarFreq("c", 10), ab, ab);
		System.out.println(ab2.vide());
		System.out.println("arbre ab2:");
		ab2.afficher();

		if (ab2.contenue(L2, ab2)) {
			System.out.println("Elle est contenue ");

		} else {
			System.out.println("Elle n'est pas contenue");
		}

		if (ab.contenue(L, ab)) {
			System.out.println("Elle est contenue ");

		} else {
			System.out.println("Elle n'est pas contenue");
		}

		if (ab.contenue(L2, ab)) {
			System.out.println("Elle est contenue ");

		} else {
			System.out.println("Elle n'est pas contenue");
		}

	}

	public void coder(ArrayList<CarFreq> codes) {
		
		//Construction du tableau de codes binaires 
		
		//Propagation du codage depuis la racine de l'arbre complet
		if(this.Gauche() != null) {
			Gauche().coder(codes, "0");
		}
		
		if(this.Droit() != null) {
			Droit().coder(codes, "1");
		}
		
		if (this.estFeuille() == true)  {
			codes.add(this.valeur);
		}
		
	}
	
	public void coder(ArrayList<CarFreq> codes, String codePere) {
		this.valeur.code = codePere;
		
		if(this.Gauche() != null) {
			Gauche().coder(codes, codePere + "0");
		}

		if(this.Droit() != null) {
			Droit().coder(codes, codePere + "1");
		}
		
		if (this.estFeuille() == true)  {
			codes.add(this.valeur);
		}
	}
	
	private boolean estFeuille() {
		return (this.Gauche() == null && this.Droit() == null);
	}
	
	public int nbFeuilles() {
		if(this.estFeuille()) {
			return 1;
		}
		
		int nbFeuilles = 0;
		
		if(this.Gauche() != null) {
			nbFeuilles += Gauche().nbFeuilles();
		}

		if(this.Droit() != null) {
			nbFeuilles += Droit().nbFeuilles();
		}
		
		return nbFeuilles;
	}
	

}