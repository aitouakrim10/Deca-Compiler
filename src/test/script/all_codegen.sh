#! /bin/sh

# Auteur : gl31
# Version initiale : 01/01/2024

# Encore un test simpliste. On compile un fichier (cond0.deca), on
# lance ima dessus, et on compare le résultat avec la valeur attendue.

# Ce genre d'approche est bien sûr généralisable, en conservant le
# résultat attendu dans un fichier pour chaque fichier source.
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:./src/main/bin:"$PATH"

# On ne teste qu'un fichier. Avec une boucle for appropriée, on
# pourrait faire bien mieux ...

echo " Debut des tests valides partie C"
dossier_tests="./src/test/deca/codegen/valid/created/sans_objet/auto_test"

for cas_de_test in "$dossier_tests"/*.deca
do
    
    filename=$(basename "$cas_de_test")
    ass="${filename%.*}"
    chemin="$dossier_tests/$ass.ass"

   
    rm -f "$chemin" 2>/dev/null
    decac "$cas_de_test" || exit 1

    if [ ! -f "$chemin" ]; then
        echo "Fichier assembleur $ass non généré."
        exit 1
    fi

    resultat=$(ima "$chemin") || exit 1
    rm -f "$chemin"
    attendu="passed"

    if echo "$resultat" | grep -q "$attendu"; then
        echo "Test '$ass' réussi."
    else
        echo "Test '$ass' échoué. Résultat inattendu de ima:"
        echo "$resultat"
        exit 1
    fi
done


dossier_tests="./src/test/deca/codegen/valid/created/objet"

for cas_de_test in "$dossier_tests"/*.deca
do
    
    filename=$(basename "$cas_de_test")
    ass="${filename%.*}"
    chemin="$dossier_tests/$ass.ass"

   
    rm -f "$chemin" 2>/dev/null
    decac "$cas_de_test" || exit 1

    if [ ! -f "$chemin" ]; then
        echo "Fichier assembleur $ass non généré."
        exit 1
    fi

    resultat=$(ima "$chemin") || exit 1
    rm -f "$chemin"
    attendu="passed"

    if echo "$resultat" | grep -q "$attendu"; then
        echo "Test '$ass' réussi."
    else
        echo "Test '$ass' échoué. Résultat inattendu de ima:"
        echo "$resultat"
        exit 1
    fi
done