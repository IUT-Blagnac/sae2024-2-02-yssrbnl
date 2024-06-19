package iut.sae.algo;

/**
 * Classe permettant de mesurer l'efficacité des algorithmes en termes de temps d'exécution.
 * Elle teste les algorithmes RLE et unRLE sur différentes tailles d'entrées et mesure le temps total d'exécution.
 *
 * <p>Les algorithmes testés sont définis dans le tableau 'algorithmes'. Chaque algorithme est exécuté sur
 * plusieurs tailles d'entrées définies dans le tableau 'entrees'. Les temps d'exécution sont mesurés et affichés
 * pour chaque algorithme testé.</p>
 *
 * <p>Les algorithmes RLE (Run-Length Encoding) et unRLE (Reverse of Run-Length Encoding) sont exécutés sur
 * les entrées spécifiées pour mesurer leur performance en termes de temps d'exécution.</p>
 *
 * <p>Les algorithmes sont testés sur les entrées suivantes :</p>
 * <ul>
 *     <li>""</li>
 *     <li>"abc"</li>
 *     <li>"abbccc"</li>
 *     <li>"aaabaa"</li>
 *     <li>"WWWWWWWWWWWWW"</li>
 *     <li>"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"</li>
 *     <li>Une chaîne de 1000 caractères 'a'</li>
 *     <li>Une chaîne de 10000 caractères 'a'</li>
 * </ul>
 *
 * <p>Les algorithmes RLE et unRLE sont exécutés de différentes manières pour tester leurs performances
 * en fonction des implémentations et des entrées spécifiques.</p>
 *
 * @author Yassir BOULOUIHA GNAOUI
 * @see Algo
 * @see AlgoException
 * @see Runnable
 */
public class EfficaciteAlgorithme {

    /**
     * Mesure le temps d'exécution moyen d'un algorithme en millisecondes.
     *
     * @param algorithme L'algorithme à mesurer
     * @return Le temps d'exécution moyen en millisecondes
     */
    public static double mesurerTempsExecution(Runnable algorithme) {
        int nbMesures = 10; // Nombre de mesures pour calculer la moyenne
        long tempsTotal = 0;

        // Mesurer le temps d'exécution nbMesures fois
        for (int i = 0; i < nbMesures; i++) {
            long debutTemps = System.currentTimeMillis();
            algorithme.run();
            long finTemps = System.currentTimeMillis();
            tempsTotal += (finTemps - debutTemps);
        }

        // Calculer la moyenne des temps d'exécution
        return (double) tempsTotal / nbMesures;
    }


    /**
     * Méthode principale qui teste les différents algorithmes RLE et unRLE sur plusieurs tailles d'entrées.
     * Elle mesure le temps total d'exécution de chaque algorithme et l'affiche.
     *
     * @param args Les arguments de la ligne de commande (non utilisés)
     * @see #mesurerTempsExecution(Runnable)
     */
    public static void main(String[] args) {
        // Différentes tailles d'entrées
        String[] entrees = {
                "",
                "abc",
                "abbccc",
                "aaabaa",
                "WWWWWWWWWWWWW",
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
                new String(new char[1000]).replace("\0", "a"),
                new String(new char[10000]).replace("\0", "a")
        };

        // Méthodes RLE et unRLE à tester
        String[] descriptions = {
                "RLE",
                "RLE avec itération",
                "unRLE",
                "unRLE avec itération"
        };

        // Sauvegarde des algorithmes à exécuter dans un tableau
        Runnable[] algorithmes = {
                () -> {
                    for (String entree : entrees) {
                        Algo.RLE(entree);
                    }
                },
                () -> {
                    for (String entree : entrees) {
                        try {
                            Algo.RLE(entree, 3);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                () -> {
                    for (String entree : entrees) {
                        String resultatRLE = Algo.RLE(entree);
                        try {
                            Algo.unRLE(resultatRLE);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                () -> {
                    for (String entree : entrees) {
                        String resultatRLE = null;
                        try {
                            resultatRLE = Algo.RLE(entree, 3);
                            Algo.unRLE(resultatRLE, 3);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        };

        // Mesurer et afficher les temps d'exécution
        double tempsTotalExeecution = 0;
        for (int i = 0; i < algorithmes.length; i++) {
            double tempsExecution = mesurerTempsExecution(algorithmes[i]);
            System.out.println("Temps total d'exécution pour " + descriptions[i] + ": " + String.format("%.3f", tempsExecution) + " ms");
            tempsTotalExeecution += tempsExecution;
        }
        System.out.println("Temps total d'exécution de l'algorithme : " + String.format("%.3f", tempsTotalExeecution) + " ms");
    }
}
