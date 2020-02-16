package huffman;
import java.util.Objects;

public class ListeString {
	
	    private ListeString reste = null;
	    private String tete;

	    public ListeString(String chaine) {
	        reste = new ListeString();
	        tete = chaine;
	    }

	    public ListeString(String chaine, ListeString reste) {
	        tete = chaine;
	        this.reste = reste;
	    }

	    public ListeString() {
	    }

	    String tete() {
	        return tete;
	    }

	    ListeString reste() {
	        if (Objects.isNull(reste)) {
	            return new ListeString();
	        } else {
	            return reste;
	        }
	    }

	    private static ListeString prefixer(String chaine, ListeString liste) {
	        return new ListeString(chaine, liste);
	    }
	    
	    boolean vide() {
	        return (tete == null);
	    }

	    public void add(String val) {
	        if (this.vide()) {
	        	//utiliser le maillon courant
	            tete = val;
	        } else {
	        	//ajouter un maillon en fin
	        	ListeString dernierMaillon = this;
	        	while(dernierMaillon.reste != null)  {
	        		dernierMaillon = dernierMaillon.reste;
	        	}

	        	dernierMaillon.reste = new ListeString(val, null);
	        }
	    }

	    public static void main(String[] args) {
	        ListeString liste = new ListeString();
	        System.out.println("liste vide :"+liste.vide());
	        liste.add("2");
	        liste.add("3");
	        liste.add("5");
	        liste = prefixer("7", liste);
	        System.out.println("tete de la liste : " + liste.tete());
	        afficher(liste);
	    }

	    public static void afficher(ListeString liste) {
	        if (liste.vide()) {
	            System.out.println("\n");
	        } else {
	            System.out.print(String.format("%s ", liste.tete()));
	            afficher(liste.reste());
	        }
	    }
	    
	    public String get(int searchIndex) {
	    	ListeString liste = this;
	    	int index = 0;
	    	while (liste.vide() != true && searchIndex > index)  {
	    		liste = liste.reste();
	    		index++;
	    	}
	    	
	    	return liste.tete();
	    }

	    public String getTexte() {
			StringBuffer strBuf = new StringBuffer();
			
			if(tete() != null) {
				strBuf.append(this.tete());
			}
			
			if(this.reste != null) {
				strBuf.append(this.reste().getTexte());
			}
			
			return strBuf.toString();
		}
	    
	    @Override
	    public String toString() {
	        return "Liste [tete=" + tete + ", reste=" + reste + ", vide=" + this.vide() + "]";
	    }
	}



