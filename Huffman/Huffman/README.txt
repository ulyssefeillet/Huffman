---- MODE D'EMPLOI

--- Lancement du programme

Il faut lancer la fonction main() de la classe Huffman.

--- Fonctionnement

On cr�e une instance de code de Huffman pour le texte sous le dossier ressources /ressources/texte.txt.
On cr�e une liste de String pour chacune des lignes du fichier lu.
On analyse ensuite la liste des fr�quences. On peut aussi utiliser la m�thode readCSV() pour lire le fichier /ressources/freq.csv. 
Dans l'�tape suivante ,on construit l'arbre complet des fr�quences avec les codes binaires pour chaque �l�ments.
On code le texte en entr�e en une suite binaire, puis on d�code cette suite pour obtenir un texte r�sultat. Le texte est bien �gal au texte d'entr�e.
Le codage et d�codage ont donc bien fonctionn�s.

--- Remarques

Pour utiliser le code de Huffman dans un cas r�el il faudrait �crire la suite binaire dans un fichier lors du codage.
Et il faudrait recr�er une autre instance du code lors du d�codage. Cette instance lirait le fichier binaire et le fichier des fr�quences.