package huffman;

//Liste d'arbres binaires de frequences

public class ListeArbreBinFreq {

	public ListeArbreBinFreq reste = null;
	ArbreBinFreq tete = null;

	public ListeArbreBinFreq(ArbreBinFreq arbreFreq) {
		reste = null;
		tete = arbreFreq;
	}

	public ListeArbreBinFreq(ArbreBinFreq arbreFreq, ListeArbreBinFreq reste) {
		tete = arbreFreq;
		this.reste = reste;
	}

	public ListeArbreBinFreq() {

	}

	ArbreBinFreq tete() {
		return tete;
	}

	ListeArbreBinFreq reste() {
		return reste;
	}
	
	public int size() {
		
		int size = 1;
		if (this.reste != null) {
			size = 1 + this.reste.size();
		}
		
		return size;
	}
	
	/*
	 private static ListeEntier prefixer(int nb, ListeEntier liste) {
	
		return new ListeEntier(nb, liste);
	}
	 */
	
	boolean vide() {
		return tete == null;
	}

	public void add(ArbreBinFreq val) {
		if (this.vide()) {
			// utiliser le maillon courant
			tete = val;
		} else {
			// ajouter un maillon en fin
			ListeArbreBinFreq dernierMaillon = this;
			while (dernierMaillon.reste != null) {
				dernierMaillon = dernierMaillon.reste;
			}

			dernierMaillon.reste = new ListeArbreBinFreq(val, null);
		}
	}

	public void afficher() {
		if (!this.vide()) {
			this.tete().afficher();
		}
		if (this.reste() != null)  {
			System.out.println(" ");
			this.reste.afficher();
		}
	}

	public ArbreBinFreq get(int searchIndex) {
		ListeArbreBinFreq liste = this;
		int index = 0;
		while (liste.vide() != true && searchIndex > index) {
			liste = liste.reste();
			index++;
		}

		return liste.tete();
	}

	@Override
	public String toString() {
		return "Liste [tete=" + tete + ", reste=" + reste + ", vide=" + vide() + "]";
	}

	public ListeArbreBinFreq getByKey(char c) {
		
		if (this.tete() == null) {
			return null;
		}
		
		if(this.tete().racine().chaine.charAt(0) == c) {
			return this;
		}
		
		if (this.reste != null) {
			return (this.reste.getByKey(c));
		}
		
		return null;
		
	}
}
