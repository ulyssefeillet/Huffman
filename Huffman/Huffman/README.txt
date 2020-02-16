---- MODE D'EMPLOI

--- Lancement du programme

Il faut lancer la fonction main() de la classe Huffman.

--- Fonctionnement

On crée une instance de code de Huffman pour le texte sous le dossier ressources /ressources/texte.txt.
On crée une liste de String pour chacune des lignes du fichier lu.
On analyse ensuite la liste des fréquences. On peut aussi utiliser la méthode readCSV() pour lire le fichier /ressources/freq.csv. 
Dans l'étape suivante ,on construit l'arbre complet des fréquences avec les codes binaires pour chaque éléments.
On code le texte en entrée en une suite binaire, puis on décode cette suite pour obtenir un texte résultat. Le texte est bien égal au texte d'entrée.
Le codage et décodage ont donc bien fonctionnés.

--- Remarques

Pour utiliser le code de Huffman dans un cas réel il faudrait écrire la suite binaire dans un fichier lors du codage.
Et il faudrait recréer une autre instance du code lors du décodage. Cette instance lirait le fichier binaire et le fichier des fréquences.