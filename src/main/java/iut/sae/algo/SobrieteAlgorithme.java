package iut.sae.algo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class SobrieteAlgorithme {

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

        double totalCpuTime = 0;
        double totalUsedMemory = 0;

        // Mesurer et afficher les ressources CPU et mémoire utilisées
        for (int i = 0; i < algorithmes.length; i++) {
            System.out.println("Mesure de la consommation des ressources pour " + descriptions[i]);
            double[] results = mesurerConsommation(algorithmes[i]);
            double cpuTime = results[0];
            double usedMemory = results[1];

            System.out.println(String.format("Temps CPU: %.3f µs", cpuTime));
            System.out.println(String.format("Mémoire utilisée: %.3f KB", usedMemory));
            System.out.println(); // Ligne vide pour séparer les résultats des différents algorithmes

            totalCpuTime += cpuTime;
            totalUsedMemory += usedMemory;
        }

        System.out.println(String.format("Temps CPU total: %.3f µs", totalCpuTime));
        System.out.println(String.format("Mémoire totale utilisée: %.3f KB", totalUsedMemory));
    }

    public static double[] mesurerConsommation(Runnable algorithme) {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

        // Vérifier si la mesure du temps CPU est supportée et activer si nécessaire
        if (!threadBean.isThreadCpuTimeSupported()) {
            System.err.println("La mesure du temps CPU n'est pas supportée par cette JVM.");
            return new double[]{0, 0};
        }
        if (!threadBean.isThreadCpuTimeEnabled()) {
            threadBean.setThreadCpuTimeEnabled(true);
        }

        // Forcer une collecte des ordures avant la mesure de la mémoire
        System.gc();

        long beforeCpuTime = threadBean.getCurrentThreadCpuTime();
        long beforeNanoTime = System.nanoTime();
        long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        algorithme.run();

        long afterCpuTime = threadBean.getCurrentThreadCpuTime();
        long afterNanoTime = System.nanoTime();
        long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long cpuTime = afterCpuTime - beforeCpuTime; // Temps en nanosecondes
        long wallClockTime = afterNanoTime - beforeNanoTime; // Temps mur en nanosecondes
        long usedMemory = afterUsedMemory - beforeUsedMemory; // Mémoire en octets

        // On choisit d'utiliser le temps CPU ou le temps mur en fonction de ce qui est disponible
        if (cpuTime <= 0) {
            cpuTime = wallClockTime;
        }

        double cpuTimeMicros = cpuTime / 1_000.0; // Conversion en microsecondes
        double usedMemoryKB = usedMemory / 1024.0; // Conversion en kilooctets

        return new double[]{cpuTimeMicros, usedMemoryKB};
    }
}
