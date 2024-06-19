
def RLE(chaine_de_caractere):
    if len(chaine_de_caractere) == 0:
        return ""

    resultat = ""
    compteur = 1
    premier_caractere = chaine_de_caractere[0]
    longueur = len(chaine_de_caractere)

    for i in range(1, longueur):
        if chaine_de_caractere[i] == premier_caractere:
            compteur += 1
            if compteur == 9:
                resultat += str(compteur) + premier_caractere
                compteur = 0
        else:
            if compteur > 0:
                resultat += str(compteur) + premier_caractere
            premier_caractere = chaine_de_caractere[i]
            compteur = 1

    if compteur > 0:
        resultat += str(compteur) + premier_caractere

    return resultat


def unRLE(chaine_compressee):
    resultat = ""
    i = 0
    longueur = len(chaine_compressee)

    while i < longueur:
        caractere = chaine_compressee[i]

        if caractere.isdigit():
            nombre = int(caractere)
            caractere_suivant = chaine_compressee[i + 1]
            resultat += nombre * caractere_suivant
            i += 2
        else:
            resultat += caractere
            i += 1

    return resultat

def unRLE_Recursif(chaineCompressee, iteration):
    resultat = chaineCompressee
    for i in range(iteration):
        resultat = unRLE(resultat)
    return resultat

def RLE_Recursif(chaine_de_caractere, iteration):
    resultat = chaine_de_caractere
    for i in range(iteration):
        resultat = RLE(resultat)
    return resultat

