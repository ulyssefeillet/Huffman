package huffman;

//PERMET DE CREER DES COUPLES (Caractere, frequence)

public class CarFreq {
	public String chaine;
	public int freq;
	public String code;
	
	public CarFreq() {

	}

	public CarFreq(String chaine, int freq) {
		this.chaine = chaine;
		this.freq = freq;
	}
	
	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("(" + chaine + ", " + freq + ", " );
		
		if (code != null)  {
			strBuf.append(code);
		} 
		strBuf.append(")");
		
		return strBuf.toString();
	}

}
