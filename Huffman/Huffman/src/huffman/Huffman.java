package huffman;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*; 

//Ulysse Feillet - Antoine De-Gryse - TP3B2 

public class Huffman {

	private ListeString lines;
	private ListeArbreBinFreq carFreqs = new ListeArbreBinFreq();
	public ArrayList<CarFreq> codeLettres = new ArrayList<CarFreq>(); 

	public Huffman(String filename) {
		//Lire fichier texte à coder
		this.lireFichier(filename);
		
		//Analyser les frequences de caracteres
		this.analyserFreq();
		
		//Lister les frequences
		this.carFreqs.afficher();
		
		//Construire l'arbre de codage
		ArbreBinFreq arbreBinaire = this.construireArbreComplet();
		arbreBinaire.afficher();
		System.out.println();
	}

	public static void main(String[] args) {

		//test();
		
		Huffman codeHuffman = new Huffman("ressources/texte.txt");
	
		System.out.println("tableau de codes: ");
		codeHuffman.afficherCodes();
		System.out.println();
		
		System.out.println("texte à coder: " + codeHuffman.getTexte());
		String texteCode = codeHuffman.coderTexte();
		System.out.println("texte codé: " + texteCode);
		System.out.println();
		
		String texteDecode = codeHuffman.decoderTexte(texteCode);
		System.out.println("texte codé puis décodé :" + texteDecode);
		
		//Verification
		if (codeHuffman.getTexte().compareTo(texteDecode) == 0) {
			System.out.println("--- Codage & decodage OK");
		} else  {
			System.out.println("--- Erreur de Codage & decodage");
		}
		
	}

	private ListeArbreBinFreq analyserFreq() {
		String texte = getTexte();
		for(int i = 0; i < texte.length(); i++) {
			char c = texte.charAt(i);
			ListeArbreBinFreq maillonPresent = carFreqs.getByKey(c);
			if (maillonPresent != null) {
				maillonPresent.tete().racine().freq++;
			} else {
					ArbreBinFreq arbreFreq = new ArbreBinFreq(String.valueOf(c), 1);
					carFreqs.add(arbreFreq);
			}
			
		}
		return carFreqs;
	}
	
	
	/*public static void test() {
		Huffman codeHuffman = new Huffman();
		
		// test liste 1 element
		CarFreq carFreq = new CarFreq("a", 1);
		codeHuffman.add(carFreq);
		System.out.println("taille avant extraction: " + codeHuffman.carFreqSize());
		ArbreBinFreq min = codeHuffman.extraireCarFreqMin();
		System.out.println("taille apres extraction: " + codeHuffman.carFreqSize());
		
		// test liste 1 element
		System.out.println("--- Test 2 ---");
		codeHuffman = new Huffman();
		
		carFreq = new CarFreq("a", 1);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("b", 2);
		codeHuffman.add(carFreq);
		
		System.out.println("---- Liste ----");
		codeHuffman.carFreqs.afficher();
		System.out.println();
		
		System.out.println("taille avant extraction: " + codeHuffman.carFreqSize());
		min = codeHuffman.extraireCarFreqMin();
		System.out.println("taille apres extraction: " + codeHuffman.carFreqSize());
		
		System.out.println("---- Liste ----");
		codeHuffman.carFreqs.afficher();
		System.out.println();
		
		// test 3
		System.out.println("--- Test 3 ---");
		codeHuffman = new Huffman();
		
		carFreq = new CarFreq("a", 1);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("b", 2);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("c", 4);
		codeHuffman.add(carFreq);
		
		ArbreBinFreq arbreBinaire = codeHuffman.construireArbreComplet();
		arbreBinaire.afficher();
		System.out.println();
		
		System.out.println("tableau de codes: ");
		codeHuffman.afficherCodes();
		
		String texteACoder = "abc";
		System.out.println("texte à coder: " + texteACoder);
		String texteSortie = codeHuffman.coderTexte(texteACoder);
		System.out.println("texte codé: " + texteSortie);
		
		String texteDecodage = codeHuffman.decoderTexte(texteSortie);
		System.out.println("texte codé puis décodé :" + texteDecodage);
		
		// test 4
		System.out.println("--- Test 4 ---");
		codeHuffman = new Huffman();
				
		carFreq = new CarFreq("a", 1);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("b", 2);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("c", 4);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("d", 5);
		codeHuffman.add(carFreq);
				
		arbreBinaire = codeHuffman.construireArbreComplet();
		arbreBinaire.afficher();
		
		// test 5
		System.out.println("--- Test 5 ---");
		codeHuffman = new Huffman();
				
		carFreq = new CarFreq("r", 5);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("e", 9);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("c", 12);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("b", 13);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("d", 16);
		codeHuffman.add(carFreq);
		carFreq = new CarFreq("a", 45);
		codeHuffman.add(carFreq);
				
		arbreBinaire = codeHuffman.construireArbreComplet();
		arbreBinaire.afficher();
	}
*/
	
	public void add(ArbreBinFreq c) {
		if (carFreqs == null) {
			this.carFreqs = new ListeArbreBinFreq(c);
		} else  {
			this.carFreqs.add(c);	
		} 
	}
	
	public void add(CarFreq cf) {
		this.carFreqs.add(new ArbreBinFreq(cf));	
	}
	
	public ListeString lireFichier(String fileName) {

		try {
			Path chemin = FileSystems.getDefault().getPath(fileName);

			boolean estLisible = Files.isRegularFile(chemin) & Files.isReadable(chemin);
			System.out.println(chemin + " est lisible : " + estLisible);

			// lecture
			BufferedReader readBuffer = Files.newBufferedReader(chemin, Charset.defaultCharset());

			String ligne = "";
			while ((ligne = readBuffer.readLine()) != null) {
				// lines.add(ligne);
				if (lines == null) {
					lines = new ListeString(ligne);
				} else {
					lines = new ListeString(ligne, lines);
				}
				System.out.println(ligne);
			}
		} catch (Exception ex) {

		} finally {
			System.out.println("The 'try catch' is finished.");
		}
		return lines;
	}
	
	public String getTexte() {
		return lines.getTexte();
	}

 	private ListeString decodeCSVLine(String ligne) {
		ListeString listeString = new ListeString();

		int indexCar = 0;
		int beginIndex = 0;
		String subString;
		boolean inString = false;
		boolean carProcessed = false;

		char car = ligne.charAt(indexCar);

		while (indexCar < ligne.length()) {
			car = ligne.charAt(indexCar);

			if (!carProcessed && car == '"' && indexCar == 0) { // Début de chaine
				inString = true;
				carProcessed = true;
			}
			if (!carProcessed && car == ',' && inString == false) { // Separateur valide
				subString = ligne.substring(beginIndex, indexCar);

				// Enleve les " en debut et fin
				if (subString != null && !subString.isEmpty()) {
					if (subString.charAt(0) == '"') {
						subString = subString.substring(1);
					}
					if (subString.charAt(subString.length() - 1) == '"') {
						subString = subString.substring(0, subString.length() - 2);
					}
				}
				listeString.add(subString);

				beginIndex = indexCar + 1;
				carProcessed = true;
			}
			if (!carProcessed && car == '"' && inString == true && ligne.charAt(indexCar + 1) == ',') { // Fin de chaine
																										// avec
																										// guillement en
																										// premiere
																										// colonne
				inString = false;
				carProcessed = true;
			}

			indexCar++;
			carProcessed = false;
		}

		// Derniere colonne
		subString = ligne.substring(beginIndex, indexCar);
		listeString.add(subString);

		return listeString;

	}

	private ArbreBinFreq extraireCarFreqMin() {
		ListeArbreBinFreq curseur = carFreqs;
		ListeArbreBinFreq min = carFreqs;
		ListeArbreBinFreq precedent = null;
		ListeArbreBinFreq precedentMin = null;

		while (curseur != null) {
			ArbreBinFreq courant = curseur.tete();
			if (courant.racine().freq < min.tete.racine().freq) {
				min = curseur;
				precedentMin = precedent;
			}
			precedent = curseur;
			curseur = curseur.reste();
		}

		// suppression dans liste chaînée de la freq
		if (precedentMin != null) {
			// cas le maillon min n'est pas le premier
			precedentMin.reste = min.reste;
		} else  {
			// cas le maillon min est le premier
			carFreqs = min.reste();
		}

		return min.tete;
	}	
	
	public void afficherCodes() {
		Iterator<CarFreq> it = codeLettres.iterator();
		while (it.hasNext()) {
			CarFreq carFreq = it.next();
			System.out.println(carFreq.chaine + ", " + carFreq.code);
		}
	}
	
	public String coderTexte() {
		return this.coderTexte(this.getTexte());
	}
	
	private String coderTexte(String texte) {
		StringBuffer codeBin = new StringBuffer();
		
		for(int i = 0; i< texte.length(); i++) {
			char c = texte.charAt(i);
			String code = getCode(c);
			codeBin.append(code);
		}
		
		return codeBin.toString();
	}
	
	public String decoderTexte(String code) {
		StringBuffer outputBuffer = new StringBuffer();
		String input = new String(code);
		ContexteDecodage contexteDecodage = new ContexteDecodage();
		
		while (input.length() > 0) {
			char c = nextMatch(input, contexteDecodage);
			outputBuffer.append(c);
			input = input.substring(contexteDecodage.dernierMatch.length());
		}
		
		return outputBuffer.toString();
	}
	
	public char nextMatch(String texte, ContexteDecodage contexteDecodage) {
		StringBuffer fragmentBuffer = new StringBuffer();
		boolean decodage = false;
		
		for (int i = 0; i < texte.length() && decodage == false; i++) {
			char c = texte.charAt(i);
			fragmentBuffer.append(c);
			decodage = decoder(fragmentBuffer.toString(), contexteDecodage);
			if (decodage) {
				contexteDecodage.dernierMatch = fragmentBuffer.toString();
			}
		}
		
		return contexteDecodage.caractere;
	}
	
	private boolean decoder(String fragment, ContexteDecodage contexteDecodage) {
		Iterator<CarFreq> it = codeLettres.iterator();
		boolean decodage = false;
		
		while (it.hasNext() && decodage == false) {
			CarFreq carFreq = it.next();
			if(carFreq.code.equals(fragment)) {
				contexteDecodage.caractere = carFreq.chaine.charAt(0);
				decodage = true;
			}
		}		
		
		return decodage;
	}

	private String getCode(char clef) {
		Iterator<CarFreq> it = codeLettres.iterator();
		while (it.hasNext()) {
			CarFreq carFreq = it.next();
			if (carFreq.chaine == null)  {
				System.out.println("Clef caractere nulle");
			}
			if(carFreq.chaine.charAt(0) == clef) {
				return carFreq.code;
			}
		}
		
		return null;
	}
	
	public ArbreBinFreq construireArbreComplet() {
        //Construction d'un arbre binaire complet
		
		ArbreBinFreq arbreBinRacine = null;
			
		while(carFreqs.size() > 1) { 
			arbreBinRacine = construireArbre();	
		}
		
		// Codage
		int nbFeuilles = arbreBinRacine.nbFeuilles();
		
		arbreBinRacine.coder(codeLettres);
		CarFreq[] tableauCodes = new CarFreq[nbFeuilles];
		codeLettres.toArray(tableauCodes);
		
		return arbreBinRacine;
	}

	public void afficherArbre() {
		System.out.println("");
		System.out.println("--- Liste d'arbres de frequence ---");
		if (carFreqs == null) {
			System.out.println("vide");
		} else {
			carFreqs.afficher();
		}
		System.out.println();
	}
	
	public ArbreBinFreq construireArbre() {
		
		ArbreBinFreq arbreBinGauche = null;
		ArbreBinFreq arbreBinDroit = null;
		
		//Liste avant extraction
		afficherArbre();
		
		// trouver les deux fréquences les plus basses
		ArbreBinFreq arbre1 = extraireCarFreqMin();
		//afficherArbre();
		
		ArbreBinFreq arbre2 = extraireCarFreqMin();
		//afficherArbre();
		
		if( arbre1.racine().freq > arbre2.racine().freq) {
			arbreBinGauche = arbre2;
			arbreBinDroit = arbre1; 
		}
		else {
			arbreBinGauche = arbre1;
			arbreBinDroit = arbre2; 
		}

		//Ajoute un nouveau noeud
		CarFreq carFreqRacine = new CarFreq(arbreBinGauche.racine().chaine + arbreBinDroit.racine().chaine, arbreBinGauche.racine().freq +  arbreBinDroit.racine().freq);
		ArbreBinFreq arbreBinRacine = new ArbreBinFreq(carFreqRacine, arbreBinGauche, arbreBinDroit);
		add(arbreBinRacine);
		
		afficherArbre();
		
		return arbreBinRacine;

	}

	private ListeArbreBinFreq readCSV(String fileName) {
		carFreqs = new ListeArbreBinFreq();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				ListeString values = decodeCSVLine(line);
				ArbreBinFreq arbreFreq = new ArbreBinFreq(values.get(0), Integer.parseInt(values.get(1)));
				carFreqs.add(arbreFreq);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return carFreqs;
	}
}
