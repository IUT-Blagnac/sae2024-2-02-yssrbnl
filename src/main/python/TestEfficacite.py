from time import time

class Algo:
    @staticmethod
    def RLE(entree, n=None):
        # Implémentation factice de RLE (Run-Length Encoding)
        pass

    @staticmethod
    def unRLE(entree, n=None):
        # Implémentation factice de unRLE (Reverse of Run-Length Encoding)
        pass

class EfficaciteAlgorithme:
    """
    Classe permettant de mesurer l'efficacité des algorithmes en termes de temps d'exécution.
    Elle teste les algorithmes RLE et unRLE sur différentes tailles d'entrées et mesure le temps total d'exécution.

    Les algorithmes testés sont définis dans le tableau 'algorithmes'. Chaque algorithme est exécuté sur
    plusieurs tailles d'entrées définies dans le tableau 'entrees'. Les temps d'exécution sont mesurés et affichés
    pour chaque algorithme testé.

    Les algorithmes RLE (Run-Length Encoding) et unRLE (Reverse of Run-Length Encoding) sont exécutés sur
    les entrées spécifiées pour mesurer leur performance en termes de temps d'exécution.

    Les algorithmes sont testés sur les entrées suivantes :
    - ""
    - "abc"
    - "abbccc"
    - "aaabaa"
    - "WWWWWWWWWWWWW"
    - "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    - Une chaîne de 1000 caractères 'a'
    - Une chaîne de 10000 caractères 'a'

    Les algorithmes RLE et unRLE sont exécutés de différentes manières pour tester leurs performances
    en fonction des implémentations et des entrées spécifiques.
    """

    @staticmethod
    def mesurer_temps_execution(algorithme):
        """
        Mesure le temps d'exécution moyen d'un algorithme en millisecondes.

        :param algorithme: L'algorithme à mesurer
        :return: Le temps d'exécution moyen en millisecondes
        """
        nb_mesures = 10  # Nombre de mesures pour calculer la moyenne
        temps_total = 0

        # Mesurer le temps d'exécution nb_mesures fois
        for _ in range(nb_mesures):
            debut_temps = time()
            algorithme()
            fin_temps = time()
            temps_total += (fin_temps - debut_temps) * 1000  # Convertir en millisecondes

        # Calculer la moyenne des temps d'exécution
        return temps_total / nb_mesures

    @staticmethod
    def main():
        # Différentes tailles d'entrées
        entrees = [
            "",
            "abc",
            "abbccc",
            "aaabaa",
            "WWWWWWWWWWWWW",
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "a" * 1000,
            "a" * 10000
        ]

        # Méthodes RLE et unRLE à tester
        descriptions = [
            "RLE",
            "RLE avec itération",
            "unRLE",
            "unRLE avec itération"
        ]

        # Exemples d'exécution des algorithmes RLE et unRLE
        algorithmes = [
            lambda: [Algo.RLE(entree) for entree in entrees],
            lambda: [Algo.RLE(entree, 3) for entree in entrees],
            lambda: [Algo.unRLE(Algo.RLE(entree)) for entree in entrees],
            lambda: [Algo.unRLE(Algo.RLE(entree, 3)) for entree in entrees]
        ]

        # Mesurer et afficher les temps d'exécution
        temps_total_execution = 0
        for i in range(len(algorithmes)):
            temps_execution = EfficaciteAlgorithme.mesurer_temps_execution(algorithmes[i])
            print(f"Temps total d'exécution pour {descriptions[i]}: {temps_execution:.3f} ms")
            temps_total_execution += temps_execution

        print(f"Temps total d'exécution de l'algorithme : {temps_total_execution:.3f} ms")


if __name__ == "__main__":
    EfficaciteAlgorithme.main()
