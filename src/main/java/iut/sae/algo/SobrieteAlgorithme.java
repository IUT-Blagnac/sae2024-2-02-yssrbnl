package iut.sae.algo;

public class SobrieteAlgorithme {

    /**
     * Méthode principale qui teste les différents algorithmes RLE et unRLE sur plusieurs tailles d'entrées.
     *
     * @param args Les arguments de la ligne de commande (non utilisés)
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

        // Exécuter les algorithmes
        for (Runnable algorithme : algorithmes) {
            algorithme.run();
        }
    }
}
